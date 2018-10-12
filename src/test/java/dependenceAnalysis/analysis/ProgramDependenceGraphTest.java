package dependenceAnalysis.analysis;

import dependenceAnalysis.util.cfg.CFGExtractor;
import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

import java.io.InputStream;
import java.util.List;

/**
 * Created by neilwalkinshaw on 22/12/2017.
 */
public class ProgramDependenceGraphTest {
    @Test
    public void computeTightness() throws Exception {

        ClassNode cn = new ClassNode(Opcodes.ASM4);
        InputStream in=CFGExtractor.class.getResourceAsStream("/java/awt/geom/Area.class");
        ClassReader classReader=new ClassReader(in);
        classReader.accept(cn, 0);
        for(MethodNode mn : (List<MethodNode>)cn.methods){
            //Run the post dominator tree generation code.
            ProgramDependenceGraph pdg = new ProgramDependenceGraph(cn,mn);
            double tightness = pdg.computeTightness();
            System.out.println(tightness);
        }


    }

}