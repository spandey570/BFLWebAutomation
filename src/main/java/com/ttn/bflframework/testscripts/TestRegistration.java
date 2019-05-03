package com.ttn.bflframework.testscripts;

import com.ttn.bflframework.pages.RegistrationPage;
import com.ttn.bflframework.utils.BaseUtils;
import com.ttn.bflframework.utils.UIUtils;
import org.testng.annotations.Test;

public class TestRegistration extends BaseUtils {

    @Test
    public void fillValidFirstName()
    {
        utils = new UIUtils(driver,testReport);
        RegistrationPage rp = new RegistrationPage(utils);
        rp.fillFirstName("Srikant");
    }

    @Test
    public void fillValidLastName()
    {
        RegistrationPage rp = new RegistrationPage(utils);
        rp.fillLastName("Pandey");
    }
//
//    @Test
//    public void selectValidGender()
//    {
//        RegistrationPage rp = new RegistrationPage(utils);
//        rp.selectMale();
//    }

}
