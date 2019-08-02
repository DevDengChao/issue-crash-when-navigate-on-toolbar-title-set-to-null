package demo.crash.on.set.title;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

/**
 * @author <a href="mailto://qq2325690622@gmail.com">Deng Chao</a> on 2019/8/1
 */
public class MainFragment extends Fragment {

    private static final String TAG = "MainFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");

        // in some case that we need to hide the title when specified fragment is shown
        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        if (actionBar != null) {
            // this is the key line which cause the crash
            actionBar.setTitle(null);
        }

        // simulate user navigate to another fragment later by click
        // as another fragment has a title, this action may throws IllegalStateException
        new Handler().postDelayed(() -> Navigation.findNavController(requireView())
                .navigate(R.id.action_mainFragment_to_anotherFragment), 150 /* any delay */);

        // FIXME: 2019/8/2
        //  java.lang.IllegalStateException: The specified child already has a parent. You must call removeView() on the child's parent first.
        //   at android.view.ViewGroup.addViewInner(ViewGroup.java:5038)
        //   at android.view.ViewGroup.addView(ViewGroup.java:4869)
        //   at android.view.ViewGroup.addView(ViewGroup.java:4841)
        //   at androidx.appcompat.widget.Toolbar.addSystemView(Toolbar.java:1525)
        //   at androidx.appcompat.widget.Toolbar.setTitle(Toolbar.java:774)
        //   at androidx.navigation.ui.ToolbarOnDestinationChangedListener.setTitle(ToolbarOnDestinationChangedListener.java:63)
        //   at androidx.navigation.ui.AbstractAppBarOnDestinationChangedListener.onDestinationChanged(AbstractAppBarOnDestinationChangedListener.java:104)
        //   at androidx.navigation.ui.ToolbarOnDestinationChangedListener.onDestinationChanged(ToolbarOnDestinationChangedListener.java:58)
        //   at androidx.navigation.NavController.dispatchOnDestinationChanged(NavController.java:372)
        //   at androidx.navigation.NavController.navigate(NavController.java:897)
        //   at androidx.navigation.NavController.navigate(NavController.java:793)
        //   at androidx.navigation.NavController.navigate(NavController.java:730)
        //   at androidx.navigation.NavController.navigate(NavController.java:716)
        //   at androidx.navigation.NavController.navigate(NavController.java:704)

    }
}
