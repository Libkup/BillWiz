package com.billwiz.admin.billwiz.fragment;

import com.billwiz.admin.billwiz.ui.CustomTitleSliderView;

import java.util.ArrayList;

/**
 * Created by 李本康 on 2018/9/9
 */
public class BillWizFragmentManager {

    public static int MAIN_ACTIVITY_FRAGMENT = 0;
    public static EditMoneyFragment mainActivityEditMoneyFragment = null;
    public static EditRemarkFragment mainActivityEditRemarkFragment = null;

    public static int EDIT_RECORD_ACTIVITY_FRAGMENT = 1;
    public static EditMoneyFragment editRecordActivityEditMoneyFragment = null;
    public static EditRemarkFragment editRecordActivityEditRemarkFragment = null;

    //改密码
    // public static PasswordChangeFragment passwordChangeFragment[] = new PasswordChangeFragment[3];

    public static ArrayList<TagChooseFragment> tagChooseFragments = new ArrayList<>();

    public static final int NUMBER_SLIDER = 0;
    public static final int EXPENSE_SLIDER = 1;
    public static CustomTitleSliderView numberCustomTitleSliderView = null;
    public static CustomTitleSliderView expenseCustomTitleSliderView = null;

    //查账的时间视图
    // public static ReportViewFragment reportViewFragment = null;

































    private static BillWizFragmentManager ourInstance = new BillWizFragmentManager();

    public static BillWizFragmentManager getInstance() {
        return ourInstance;
    }

    private BillWizFragmentManager() {
    }
}
