package jagerfield.permissions_and_utilities_library.SoftKeyboradUtil;

import android.app.Activity;
import android.view.inputmethod.InputMethodManager;

public class SoftKeyboardUtil
{
    public static void setSkb(Activity activity, boolean mode)
    {
        if (activity == null)
        {
            return;
        }

        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(activity.INPUT_METHOD_SERVICE);

        if (mode)
        {
            inputMethodManager.toggleSoftInput(0, InputMethodManager.SHOW_IMPLICIT);
        }
        else {
            if (inputMethodManager != null)
            {
                if (activity == null)
                    return;
                if (activity.getCurrentFocus() == null)
                    return;
                if (activity.getCurrentFocus().getWindowToken() == null)
                    return;
                inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
            }
        }
    }
}
