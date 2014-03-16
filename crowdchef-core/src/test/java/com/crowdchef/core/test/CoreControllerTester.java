package com.crowdchef.core.test;


import com.crowdchef.core.CoreController;

public class CoreControllerTester {
    public static void main(String[] args) {
        CoreController controller = new CoreController();
        System.out.println(controller.getUserInfo(new Long(1)));
    }
}
