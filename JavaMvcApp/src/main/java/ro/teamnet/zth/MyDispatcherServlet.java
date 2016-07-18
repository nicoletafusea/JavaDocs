package ro.teamnet.zth;


import org.codehaus.jackson.map.ObjectMapper;
import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.api.annotations.MyRequestObject;
import ro.teamnet.zth.api.annotations.MyRequestParam;
import ro.teamnet.zth.appl.controller.DepartmentController;
import ro.teamnet.zth.appl.controller.EmployeeController;
import ro.teamnet.zth.fmk.AnnotationScanUtils;
import ro.teamnet.zth.fmk.MethodAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by user on 7/14/2016.
 */
public class MyDispatcherServlet extends HttpServlet {
    //rol de registru
    //key: url path
    //val: info despre metoda care va procesa urlul
    HashMap<String, MethodAttributes> allowedMethods = new HashMap<String, MethodAttributes>();

    @Override
    public void init() throws ServletException {
        try {
            Iterable<Class> classes =
                    AnnotationScanUtils.getClasses("ro.teamnet.zth.appl.controller");
            for (Class controller : classes) {
                if (controller.isAnnotationPresent(MyController.class)) {
                    MyController myCtrlAnnotation =
                            (MyController) controller.getAnnotation(MyController.class);
                    String controllerUrlPath = myCtrlAnnotation.urlPath();
                    Method[] controllerMethods = controller.getMethods();
                    for (Method controllerMethod : controllerMethods) {
                        if (controllerMethod.isAnnotationPresent(MyRequestMethod.class)) {
                            MyRequestMethod myMethodAnnot =
                                    (MyRequestMethod) controllerMethod.getAnnotation(MyRequestMethod.class);
                            String methodUrlPath = myMethodAnnot.urlPath();
                            //construiesc cheia pt hashmap
                            String urlPath = controllerUrlPath + methodUrlPath;

                            //construiesc valoarea
                            MethodAttributes methodAttributes = new MethodAttributes();
                            methodAttributes.setControllerClass(controller.getName());
                            methodAttributes.setMethodType(myMethodAnnot.methodType());
                            methodAttributes.setMethodName(controllerMethod.getName());
                            methodAttributes.setParameterTypes(controllerMethod.getParameterTypes());
                            allowedMethods.put(getControllerKey(myMethodAnnot.methodType(), urlPath), methodAttributes);
                        }
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

    }

    private String getControllerKey(String methodType, String urlPath) {
        return urlPath+" "+methodType;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //instructiuni de delegare
        dispatchReply("POST", req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //instructiuni de delegare
        dispatchReply("GET", req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        dispatchReply("DELETE", req, resp);
    }

    private void dispatchReply(String fct, HttpServletRequest req, HttpServletResponse res) {
        Object r = null;
        try {
            r = dispatch(req, res, fct);
        } catch (Exception ex) {
            sendExceptionError(ex, req, res);
        }
        try {
            reply(r, req, res);
        } catch (IOException e) {
            sendExceptionError(e, req, res);
            e.printStackTrace();
        }
    }

    private void reply(Object r, HttpServletRequest req, HttpServletResponse res) throws IOException {
        ObjectMapper o = new ObjectMapper();
        String resp = o.writeValueAsString(r);
        PrintWriter out = res.getWriter();
        out.print(resp);
    }

    private Object dispatch(HttpServletRequest req, HttpServletResponse res, String methodType) throws IOException {
        String pathInfo = req.getPathInfo();
        String key = getControllerKey(methodType, pathInfo);
        //caut in registru
        MethodAttributes methodAttributes = allowedMethods.get(key);
        if (methodAttributes == null) {
            return "null hello";
        } else {
            String controllerName = methodAttributes.getControllerClass();
            try {
                Class controllerClass = Class.forName(controllerName);
                Object controllerInstance = controllerClass.newInstance();
                Method method = controllerClass.getMethod(methodAttributes.getMethodName(),
                        methodAttributes.getParameterTypes());
                Parameter[] parameters = method.getParameters();
                List<Object> paramValues = new ArrayList<>();
                for (Parameter param : parameters) {
                    if (param.isAnnotationPresent(MyRequestParam.class)) {
                        MyRequestParam annotation = param.getAnnotation(MyRequestParam.class);
                        String name = annotation.name();
                        String requestParamValue = req.getParameter(name);
                        Class<?> type = param.getType();
                        Object object = null;
                        if (type.equals(String.class)) {
                            object = requestParamValue;
                        } else {
                            object = new ObjectMapper().readValue(requestParamValue, type);
                        }
                        paramValues.add(object);
                    }
                    else if (param.isAnnotationPresent(MyRequestObject.class)) {
                        BufferedReader br = req.getReader();
                        Object reqBody = new ObjectMapper().readValue(br, param.getType());
                        paramValues.add(reqBody);
                    }
                }

                return method.invoke(controllerInstance, paramValues.toArray());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            return "hello";
        }
    }

    private void sendExceptionError(Exception ex, HttpServletRequest req, HttpServletResponse res) {

    }
}
