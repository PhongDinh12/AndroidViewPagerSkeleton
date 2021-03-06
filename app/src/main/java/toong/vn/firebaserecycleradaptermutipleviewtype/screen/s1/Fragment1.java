package toong.vn.firebaserecycleradaptermutipleviewtype.screen.s1;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import toong.vn.firebaserecycleradaptermutipleviewtype.R;
import toong.vn.firebaserecycleradaptermutipleviewtype.fragment.ChildContainerFragment;

public class Fragment1 extends ChildContainerFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");
        View rootView = inflater.inflate(R.layout.fragment_1, container, false);
        rootView.findViewById(R.id.button_go_to_sub_1)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        goSub1AndAddToBackStack();
                    }
                });
        return rootView;
    }

    private void goSub1AndAddToBackStack() {
        FragmentTransaction tr = getParentFragment().getChildFragmentManager().beginTransaction();
        tr.setCustomAnimations(R.anim.slide_right_in, R.anim.slide_left_out, R.anim.slide_left_in,
                R.anim.slide_right_out);
        tr.replace(R.id.frame_container, new Fragment1sub1()).addToBackStack("")
                .commit();

//        getParentFragment().getChildFragmentManager()
//                .beginTransaction()
//                .add(R.id.frame_container, new Fragment1sub1())
//                .addToBackStack("")
//                .commit();
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }
}