package demo.crash.on.set.title;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

/**
 * @author <a href="mailto://qq2325690622@gmail.com">Deng Chao</a> on 2019/8/1
 */
public class AnotherFragment extends Fragment {

    private static final String TAG = "AnotherFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_another, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");

        // simulate the user navigate back
        Navigation.findNavController(requireView()).navigateUp();
    }
}
