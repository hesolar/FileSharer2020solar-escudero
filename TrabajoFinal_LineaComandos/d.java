package TrabajoFinal_LineaComandos;

import java.io.File;

import javax.swing.tree.DefaultMutableTreeNode;

public class d  {

       
        public void createChildren(File fileRoot,  DefaultMutableTreeNode node) {
            File[] files = fileRoot.listFiles();
            if (files == null) return;
            DefaultMutableTreeNode childNode ;
            for (File file : files) {
               
                
                if (file.isDirectory()) {
                	  childNode = new DefaultMutableTreeNode(new FileNode(file));
                	node.add(childNode);
                    createChildren(file, childNode);
                }
            }
        }

    

    public class FileNode {

        private File file;

        public FileNode(File file) {
            this.file = file;
        }

        @Override
        public String toString() {
            String name = file.getName();
            if (name.equals("")) {
                return file.getAbsolutePath();
            } else {
                return name;
            }
        }
    }
}
