package frame.asm;

import frame.PropertyMgr;
import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.MethodVisitor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static jdk.internal.org.objectweb.asm.Opcodes.ASM4;
import static jdk.internal.org.objectweb.asm.Opcodes.INVOKESTATIC;

public class ClassTransformerTest {
    public static void main(String[] args) throws IOException {
        ClassReader cr = new ClassReader(ClassPrinter.class.getClassLoader().
                getResourceAsStream("frame/asm/Tank.class"));
        ClassWriter cw= new ClassWriter(0);
        ClassVisitor cv= new ClassVisitor(ASM4,cw) {
            @Override
            public MethodVisitor visitMethod(int i, String s, String s1, String s2, String[] strings) {
               MethodVisitor mv=super.visitMethod(i,s,s1,s2,strings);
               return new MethodVisitor(ASM4,mv) {
                   @Override
                   public void visitCode() {
                       //将TimeProxy的before方法加入到Tank.class里
                       //加入自定义方法。相当于Java的汇编
                       visitMethodInsn(INVOKESTATIC,"TimeProxy","before","()V",false);
                       super.visitCode();
                   }
               };
            }
        };
        //不做修改将class文件拷贝，直接拷贝
//        cr.accept(cw,0);
        //修改class文件后将其拷贝，相当于通过CalssVisitor转换后在拷贝
        cr.accept(cv,0);
        byte[] b2 = cw.toByteArray();
        String path = PropertyMgr.getString("dir");
        File file = new File(path+"/frame/asm/");
        file.mkdirs();
        FileOutputStream fop = new FileOutputStream(new File(path+"frame/asm/Tank_01.class"));
        fop.write(b2);
        fop.flush();
        fop.close();
    }
}
