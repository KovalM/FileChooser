package chooser.consist;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class ModeListener implements ActionListener {
    protected ConsistDirectoryPanel consistDirectoryPanel;
    protected JToolBar changeConsist;

    protected ModeListener(ConsistDirectoryPanel consistDirectoryPanel, JToolBar changeConsist){
        this.changeConsist = changeConsist;
        this.consistDirectoryPanel = consistDirectoryPanel;
    }

    protected void changeMode(){}

    @Override
    public void actionPerformed(ActionEvent e) {
        JToggleButton currentButton;
        int count = changeConsist.getComponentCount();
        for (int i=0; i<count; i++){
            try {
                currentButton = (JToggleButton) changeConsist.getComponentAtIndex(i);
                if (e.getActionCommand().equals(currentButton.getActionCommand())){
                    currentButton.setSelected(true);
                }else{
                    currentButton.setSelected(false);
                }
            } catch (Exception err){
                err.printStackTrace();
            }
        }
        changeMode();
    }
}