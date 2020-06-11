package application;

import java.io.IOException;

import javax.swing.ImageIcon;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import vo.Member;

public class MemberListViewControl {

	@FXML
    private HBox hBox;
	@FXML
    private ImageView imageView;
    @FXML
    private Label nameLabel;
    @FXML
    private Label onlineLabel;
    @FXML
    private Label offlineLabel;
    
    private Image wImage;
    private Image mImage;
    
	public MemberListViewControl(Member member)
    {
		//System.out.println("Working Directory = " + System.getProperty("user.dir"));
		
		wImage = new Image("file:image/avatar1.png");
		mImage = new Image("file:image/avatar2.png");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MemberListViewCell.fxml"));
        fxmlLoader.setController(this);
        try
        {
            fxmlLoader.load();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        nameLabel.setText(member.getName());
        if (member.getGender() == Member.Female) {
        	imageView.setImage(wImage);
        } else {
        	imageView.setImage(mImage);
        }
        onlineLabel.setVisible(false);
        offlineLabel.setVisible(false);
    }

    public HBox getBox()
    {
        return hBox;
    }
	
}
