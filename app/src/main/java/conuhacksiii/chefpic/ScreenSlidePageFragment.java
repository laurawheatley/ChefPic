package conuhacksiii.chefpic;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ScreenSlidePageFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    //private static final String ARG_PARAM1 = "param1";
    //private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    //private String mParam1;
    //private String mParam2;

    private String image;
    private String title;
    private String serving;
    private String recipeURL;
    private String[] topIngredients;

    //private OnFragmentInteractionListener mListener;

    /*
    public ScreenSlidePageFragment() {
        // Required empty public constructor
    }

    public void initialize(){
        Bundle args = getArguments();

        image = args.getString("image");
        title = args.getString("title");
        serving = args.getString("serving");
        recipeURL = args.getString("url");
        topIngredients = args.getStringArray("topingredients");


    }
    */

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    // TODO: Rename and change types and number of parameters
    public static ScreenSlidePageFragment newInstance(String image, String title, String serving, String recipeURL, String[] topIngredients) {
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        Bundle args = new Bundle();
        args.putString("IMAGE", image);
        args.putString("TITLE", title);
        args.putString("URL", recipeURL);
        args.putString("SERVING", serving);
        args.putStringArray("TOPINGREDIENTS", topIngredients);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            image = getArguments().getString("IMAGE");
            title = getArguments().getString("TITLE");
            recipeURL = getArguments().getString("URL");
            serving = getArguments().getString("SERVING");
            topIngredients = getArguments().getStringArray("TOPINGREDIENTS");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_screen_slide_page, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        TextView titleV = view.findViewById(R.id.recipeTitle);
        TextView servingV = view.findViewById(R.id.recipeServing);
        TextView topIngredientsV = view.findViewById(R.id.topIngredients);

        titleV.setText(title);
        servingV.setText(serving);
        String topIngs = "";
        for (String s : topIngredients) {
            if (s != null)
                topIngs += s + "\n";
        }
        topIngredientsV.setText(topIngs);
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(View view) {
        Uri uri = Uri.parse(recipeURL);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    /*
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    */

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    /*
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    */
}
