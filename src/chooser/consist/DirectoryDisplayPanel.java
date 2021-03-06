package chooser.consist;

import chooser.choose.ChooseFilePanel;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.File;

public class DirectoryDisplayPanel extends DisplayPanel{
    DefaultListModel listModel;
    public DirectoryDisplayPanel(ConsistDirectoryPanel consistDirectoryPanel, String directoryName,ChooseFilePanel chooseFilePanel) {
        super(consistDirectoryPanel,directoryName,chooseFilePanel);
    }

    public void changedConsist(){
        listModel.clear();
        int count = listOfFiles.length;
        for (int i = 0; i < count; i++) {
            String name;
            if (listOfFiles[i].getName().equals("")){
                name = listOfFiles[i].getAbsolutePath();
            } else{
                name = listOfFiles[i].getAbsolutePath();
            }
            listModel.addElement(name);
        }
    }

    public void setProperty(){
        listModel = new DefaultListModel();
        JList listVisibly = new JList(listModel);
        listVisibly.setVisibleRowCount(11);
        listVisibly.setLayoutOrientation(JList.VERTICAL_WRAP);
        listVisibly.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listVisibly.setCellRenderer(new MyCellRenderer());
        listVisibly.addMouseListener(new ListSelectedListener(listVisibly,consistDirectoryPanel));
        //listVisibly.addListSelectionListener(new ListSelectedListener(listVisibly,consistDirectoryPanel));
        JScrollPane scroll = new JScrollPane(listVisibly);
        scroll.setPreferredSize(new Dimension(getWidth(),getHeight()));
        add(scroll, BorderLayout.CENTER);
    }

    private static class MyCellRenderer extends JLabel implements ListCellRenderer {

        private static final long serialVersionUID = 1L;

        @Override
        public Component getListCellRendererComponent(JList list, Object value,
                                                      int index, boolean isSelected, boolean cellHasFocus) {
            if (value instanceof String) {
                String name = (String) value;
                File file = new File(name);
                if (file.getName().equals("")){
                    name = file.getAbsolutePath();
                }else{
                    name = file.getName();
                }
                if (file.isDirectory()){
                    setIcon(new ImageIcon("image/folder.png"));
                }else{
                    setIcon(new ImageIcon("image/file.png"));
                }
                setText(name);
                //setVerticalTextPosition(JLabel.BOTTOM);
                //setHorizontalTextPosition(JLabel.CENTER);

                if (isSelected) {
                    setBackground(Color.lightGray);
                } else {
                    setBackground(list.getBackground());
                    setForeground(list.getForeground());
                }
                setFont(list.getFont());
                setOpaque(true);
            }
            return this;
        }
    }
}
