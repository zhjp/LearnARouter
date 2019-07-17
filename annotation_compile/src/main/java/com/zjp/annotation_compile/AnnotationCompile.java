package com.zjp.annotation_compile;

import com.google.auto.service.AutoService;
import com.zjp.annotation.BindPath;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;

@AutoService(Processor.class)    //注册注解处理器
public class AnnotationCompile extends AbstractProcessor {
    //文件对象
    Filer filer;

    /**
     * 初始化注解处理器
     * @param processingEnv
     */
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        filer = processingEnv.getFiler();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String>  types = new HashSet<>();
        types.add(BindPath.class.getCanonicalName());
        return types;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return processingEnv.getSourceVersion();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        //现在拿到的就是类节点
        Set<? extends Element> elementsAnnotatedWith = roundEnv.getElementsAnnotatedWith(BindPath.class);
        //初始化数据
        Map<String,String> map = new HashMap<>();
        for (Element element : elementsAnnotatedWith) {
            TypeElement typeElement = (TypeElement) element;
            //拿到节点的注解
            BindPath annotation = typeElement.getAnnotation(BindPath.class);
            String key = annotation.value();
            String value = typeElement.getQualifiedName().toString();
            map.put(key,value);
        }
        //写文件
        if(map.size()>0){
            Writer writer = null;
            //创建一个文件的名字
            String utilName = "ActivityUtil"+System.currentTimeMillis();
            try {
                //创建文件对象
                JavaFileObject javaFileObject = filer.createSourceFile("com.ziproute.util."+utilName);
                writer = javaFileObject.openWriter();
                writer.write("package com.ziproute.util;\n " +
                        "\n" +
                        "import com.zjp.route.ARouter;\n" +
                        "import com.zjp.route.IRoute;\n" +
                        "\n" +
                        "public class "+utilName+" implements IRoute {\n" +
                        "    @Override\n" +
                        "    public void putActivity() {\n");
                //获取map的遍历对象
                Iterator<String> iterator = map.keySet().iterator();
                while (iterator.hasNext()){
                    String path = iterator.next();
                    String value = map.get(path);
                    writer.write("ARouter.getInstance().putActivity(\""+path+"\","+
                            value+".class);\n");
                }
                writer.write("}\n}");
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                if(writer!=null){
                    try {
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return false;
    }
}
