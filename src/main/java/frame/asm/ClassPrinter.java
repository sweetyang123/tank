package frame.asm;

import jdk.internal.org.objectweb.asm.*;

import java.io.IOException;

import static jdk.internal.org.objectweb.asm.Opcodes.*;

public class ClassPrinter extends ClassVisitor {
    public ClassPrinter() {
        super(ASM4);
    }
    public void visit(int version, int access, String name,
                      String signature, String superName, String[] interfaces) {
        System.out.println(name + " extends " + superName + " {");
    }
    public FieldVisitor visitField(int access, String name, String desc,
                                   String signature, Object value) {
        System.out.println(" " + desc + " " + name);
        return null;
    }
    public MethodVisitor visitMethod(int access, String name,
                                     String desc, String signature, String[] exceptions) {
        System.out.println(" " + name + desc);
        return null;
    }
    public void visitEnd() {
        System.out.println("}");
    }

    public static void main(String[] args) throws IOException {
        //解析类 ClassReader
        ClassPrinter cp = new ClassPrinter();
//        Runnable
        ClassReader cr = new ClassReader("Test");
        cr.accept(cp, 0);
        //创建类 ClassWriter
        ClassWriter cw = new ClassWriter(0);
        cw.visit(V1_5, ACC_PUBLIC + ACC_ABSTRACT + ACC_INTERFACE,
                "frame/asm/GenerateClass", null, "java/lang/Object",null);
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "LESS", "I",
                null, new Integer(-1)).visitEnd();
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "EQUAL", "I",
                null, new Integer(0)).visitEnd();
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "GREATER", "I",
                null, new Integer(1)).visitEnd();
        cw.visitMethod(ACC_PUBLIC + ACC_ABSTRACT, "generate",
                "(Ljava/lang/Object;)I", null, null).visitEnd();
        cw.visitEnd();
        byte[] b = cw.toByteArray();
        MyClassLoader myClassLoader = new MyClassLoader();
        Class c = myClassLoader.defineClass("frame.asm.GenerateClass", b);

        System.out.println(c.getMethods()[0].getName());
        System.out.println(c.getFields()[0]);
        System.out.println(c.getConstructors());

    }
    static class MyClassLoader extends ClassLoader {
        public Class defineClass(String name, byte[] b) {
            return defineClass(name, b, 0, b.length);
        }
    }
}