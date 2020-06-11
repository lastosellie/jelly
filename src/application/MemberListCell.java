package application;

import javafx.scene.control.ListCell;
import vo.Member;

public class MemberListCell extends ListCell<Member> {

	@Override
	protected void updateItem(Member member, boolean arg1) {
		super.updateItem(member, arg1);

        if(member != null)
        {
        	MemberListViewControl control = new MemberListViewControl(member);
            setGraphic(control.getBox());
        }
	}
}
