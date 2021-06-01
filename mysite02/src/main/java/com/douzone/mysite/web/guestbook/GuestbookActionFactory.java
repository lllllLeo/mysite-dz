package com.douzone.mysite.web.guestbook;

import com.douzone.web.Action;
import com.douzone.web.ActionFactory;

public class GuestbookActionFactory extends ActionFactory{

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		System.out.println(actionName);
		if("list".equals(actionName)) {
			action = new IndexAction();
		} else if ("add".equals(actionName)) {
			action = new AddAction();
		} else if ("deleteform".equals(actionName)) {
			action = new DeleteFormAction();
		} else if ("delete".equals(actionName)) {
			action = new DeleteAction();
		}
		
		return action;
	}

}
