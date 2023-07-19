package com.example.ssaesak.Working;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ssaesak.Adapter.AgricultureAdapter;
import com.example.ssaesak.Adapter.CareerAdapter;
import com.example.ssaesak.Adapter.CropAdapter;
import com.example.ssaesak.Board.BoardActivity;
import com.example.ssaesak.Dto.WorkDTO;
import com.example.ssaesak.Farmgroup.FarmgroupActivity;
import com.example.ssaesak.Farmgroup.FarmgroupNullActivity;
import com.example.ssaesak.Main.MainActivity;
import com.example.ssaesak.Model.User;
import com.example.ssaesak.Model.UserFarmList;
import com.example.ssaesak.R;
import com.example.ssaesak.Retrofit.ApiResponse;
import com.example.ssaesak.Retrofit.MyRetrofit;
import com.example.ssaesak.Study.StudyActivity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WorkingFarmerActivity extends AppCompatActivity implements BottomsheetAreaDialog.BottomSheetAreaListener,BottomsheetCropDialog.BottomSheetCropListener,  BottomsheetCareerDialog.BottomSheetCareerListener, BottomsheetAgricultureDialog.BottomSheetAgricultureListener {
//public class WorkingActivity  extends Activity {

    private final String TAG = this.getClass().getSimpleName();

    private Spinner spinnerAgriculture;
    private Spinner spinnerCrop;
    private Spinner spinnerCareer;
    private AgricultureAdapter adapterAgriculture;
    private CropAdapter adapterCrop;
    private CareerAdapter adapterCareer;
    private String selectedAgriculture;
    private String selectedCrop;
    private String selectedCareer;

    private LinearLayout noticeList;
    private LayoutInflater layoutInflater;
    private LinearLayout card;
    private String filter;

    private LinearLayout layout;

    private List<LinearLayout> layoutList;

    private List<TextView> textList;

    private List<ImageView> imageList;

    private String filterArea;
    private String filterAgriculture;
    private String filterCrop;
    private String filterCareer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_notice);
        filter = "";

        this.layoutList = new ArrayList<>();
        this.layoutList.add(findViewById(R.id.area_layout));
        this.layoutList.add(findViewById(R.id.agriculture_layout));
        this.layoutList.add(findViewById(R.id.crop_layout));
        this.layoutList.add(findViewById(R.id.career_layout));

        this.textList = new ArrayList<>();
        this.textList.add(findViewById(R.id.area_text));
        this.textList.add(findViewById(R.id.agriculture_text));
        this.textList.add(findViewById(R.id.crop_text));
        this.textList.add(findViewById(R.id.career_text));

        this.imageList = new ArrayList<>();
        this.imageList.add(findViewById(R.id.area_image));
        this.imageList.add(findViewById(R.id.agriculture_image));
        this.imageList.add(findViewById(R.id.crop_image));
        this.imageList.add(findViewById(R.id.career_image));

        for (LinearLayout layout : layoutList) {
            layout.setOnClickListener(v -> {
                layout.setBackground(getResources().getDrawable(R.drawable.filter_select, null));
                textList.get(layoutList.indexOf(layout)).setTextColor(getResources().getColor(R.color.gray1, null));
                imageList.get(layoutList.indexOf(layout)).setBackground(getResources().getDrawable(R.drawable.svg_dropdown_select, null));
                setSelectedFalse(layout);
            });
        }



        layout = findViewById(R.id.layout_notice);
        this.filterArea = "";
        this.filterAgriculture = "";
        this.filterCrop = "";
        this.filterCareer = "";

        LinearLayout layoutArea = findViewById(R.id.area_layout);
        layoutArea.setOnClickListener(view -> {
            BottomsheetAreaDialog bottomsheetAreaDialog = new BottomsheetAreaDialog();
            Bundle args = new Bundle();
            args.putString("area", filterArea);
            bottomsheetAreaDialog.setArguments(args);
            bottomsheetAreaDialog.show(getSupportFragmentManager(), "dd");
        });

        LinearLayout layoutAgriculture = findViewById(R.id.agriculture_layout);
        layoutAgriculture.setOnClickListener(view -> {
            BottomsheetAgricultureDialog bottomsheetAgricultureDialog = new BottomsheetAgricultureDialog();
            Bundle args = new Bundle();
            args.putString("agriculture", filterAgriculture);
            bottomsheetAgricultureDialog.setArguments(args);
            bottomsheetAgricultureDialog.show(getSupportFragmentManager(), "dd");
        });

        LinearLayout layoutCrop = findViewById(R.id.crop_layout);
        layoutCrop.setOnClickListener(view -> {
            BottomsheetCropDialog bottomsheetCropDialog = new BottomsheetCropDialog();
            Bundle args = new Bundle();
            args.putString("crop", filterCrop);
            bottomsheetCropDialog.setArguments(args);
            bottomsheetCropDialog.show(getSupportFragmentManager(), "dd");
        });

        LinearLayout layoutCareer = findViewById(R.id.career_layout);
        layoutCareer.setOnClickListener(view -> {
            BottomsheetCareerDialog bottomsheetCareerDialog = new BottomsheetCareerDialog();
            Bundle args = new Bundle();
            args.putString("career", filterCareer);
            bottomsheetCareerDialog.setArguments(args);
            bottomsheetCareerDialog.show(getSupportFragmentManager(), "dd");
        });





//        for (Button button : chipList) {
//            button.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    filter = button.getText().toString();
//                    Log.e(this.toString(), filter);
//                    button.setBackground(getResources().getDrawable(R.drawable.chip_select, null));
////                    button.setPressed(true);
////                    button.setSelected(true);
//                }
//            });
//        }

//        spinnerAgriculture = findViewById(R.id.spinner_agriculture);
//        spinnerCrop = findViewById(R.id.spinner_crop);
//        spinnerCareer = findViewById(R.id.spinner_career);
//
//        this.noticeList = findViewById(R.id.layout_notice);
//

        this.layoutInflater = LayoutInflater.from(getBaseContext());
        CardWorkNotice cardView = new CardWorkNotice(getBaseContext());

        List<CardWorkNotice> cardViewList = new ArrayList<>();
        cardViewList.add(cardView);
//        this.card = (LinearLayout) layoutInflater.inflate(R.layout.card_work_notice, null, false);



        this.layoutInflater = LayoutInflater.from(getBaseContext());
        this.noticeList = findViewById(R.id.layout_notice);

        workList(User.getInstance().getUserId());
//        CardWorkNotice cardView = new CardWorkNotice(getBaseContext());
//
//        List<CardWorkNotice> cardViewList = new ArrayList<>();
//        cardViewList.add(cardView);
//
//        this.layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
//        this.card = (LinearLayout)layoutInflater.inflate(R.layout.card_work_notice, this.noticeList, false);
//        this.card.setVisibility(View.VISIBLE);
//        this.card.setOnClickListener(v -> {
//            Intent intent = new Intent(getBaseContext(), NoticeDetailActivity.class);
//            startActivity(intent);
//            overridePendingTransition(0, 0);
//        });
//
//        this.card.findViewById(R.id.bookmark).setOnClickListener(v -> {
//            if(this.card.findViewById(R.id.bookmark).isSelected()) {
//                Log.e("card", "press cancel!!");
//                this.card.findViewById(R.id.bookmark).setSelected(false);
//                this.card.findViewById(R.id.bookmark).setBackground(getResources().getDrawable(R.drawable.svg_bookmark, null));
//            } else {
//                Log.e("card", "press !!");
//                this.card.findViewById(R.id.bookmark).setSelected(true);
//                this.card.findViewById(R.id.bookmark).setBackground(getResources().getDrawable(R.drawable.svg_bookmark_select, null));
//            }
//        });
//
//        layout.addView(card);
//
//        // 스피너에 붙일 어댑터 초기화
//        adapterAgriculture = new AgricultureAdapter(this, new ArrayList<>(Arrays.asList(Constants.agricultureList)));
//        adapterCrop = new CropAdapter(this, new ArrayList<>(Arrays.asList(Constants.cropList)));
//        adapterCareer = new CareerAdapter(this, new ArrayList<>(Arrays.asList(Constants.careerList)));
//
//        spinnerAgriculture.setAdapter(adapterAgriculture);
//        spinnerCrop.setAdapter(adapterCrop);
//        spinnerCareer.setAdapter(adapterCareer);

//        // 스피너 클릭 리스너
//        spinnerAgriculture.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                selectedAgriculture = adapterAgriculture.getItem();
////                String otherItem = (String) spinner.getItemAtPosition(position);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {}
//        });
//
//        spinnerCrop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                selectedCrop = adapterCrop.getItem();
////                String otherItem = (String) spinner.getItemAtPosition(position);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {}
//        });
//
//
//        spinnerCareer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                selectedCareer = adapterCareer.getItem();
////                String otherItem = (String) spinner.getItemAtPosition(position);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {}
//        });



        // 내비 표시
        BottomNavigationView navigation = findViewById(R.id.fragment_bottom);
        Menu menu = navigation.getMenu();
        for (int i = 0; i < navigation.getMenu().size(); i++) {
            MenuItem menuItem = menu.getItem(i);
            if (menuItem.getItemId() == R.id.fragment_working) {menuItem.setChecked(true);}
        }

        navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.fragment_home) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                } else if (item.getItemId() == R.id.fragment_working) {
//                    startActivity(new Intent(getApplicationContext(), WorkingActivity.class));
//                    overridePendingTransition(0, 0);
                    return true;
                } else if (item.getItemId() == R.id.fragment_farm) {

                    if(UserFarmList.getInstance().size() < 1) {
                        startActivity(new Intent(getApplicationContext(), FarmgroupNullActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    }

                    startActivity(new Intent(getApplicationContext(), FarmgroupActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                } else if (item.getItemId() == R.id.fragment_notice) {
                    startActivity(new Intent(getApplicationContext(), BoardActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                } else if (item.getItemId() == R.id.fragment_study) {
                    startActivity(new Intent(getApplicationContext(), StudyActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                } else {
                    return false;
                }
            }
        });
    }



    @Override
    public void onBackPressed() {
        this.startActivity(new Intent(this, MainActivity.class));

        finish();
        overridePendingTransition(0, 0); //애니메이션 없애기
    }

    private void setSelectedFalse(LinearLayout layout) {
        for (LinearLayout nonLayout : layoutList) {
            if(!nonLayout.equals(layout)) {
                nonLayout.setBackground(getResources().getDrawable(R.drawable.filter_not_select, null));
                textList.get(layoutList.indexOf(nonLayout)).setTextColor(getResources().getColor(R.color.gray5, null));
                imageList.get(layoutList.indexOf(nonLayout)).setBackground(getResources().getDrawable(R.drawable.svg_dropdown, null));
            }
        }
    }

    @Override
    public void onButtonArea(String filterArea) {
        this.filterArea = filterArea;

        Log.e("filter", filterArea);

        this.workListAddress(filterArea, User.getInstance().getUserId());
    }

    @Override
    public void onButtonAgiculture(String filterAgriculture) {
        this.filterAgriculture = filterAgriculture;

        Log.e("filter", filterAgriculture);

        this.workListAgriculture(filterAgriculture, User.getInstance().getUserId());
    }

    @Override
    public void onButtonCrop(String filterCrop) {
        this.filterCrop = filterCrop;

        Log.e("filter", filterCrop);

        this.workListCrops(filterCrop, User.getInstance().getUserId());
    }

    @Override
    public void onButtonCareer(String filterCareer) {
        this.filterCareer = filterCareer;
        float career = 0;

        if (filterCareer.equals("경력 무관")) {
            career = 99;
        } else if (filterCareer.equals("0~12개월")) {
            career = 1;
        } else if (filterCareer.equals("1~3년")) {
            career = 3;
        } else if (filterCareer.equals("3~5년")) {
            career = 5;
        } else if (filterCareer.equals("5년 이상")) {
            career = 7;
        }

        Log.e("filter", filterCareer);

        this.workListCareer(career, User.getInstance().getUserId());
    }

    private void addNoticeCard(int workId, String address, String title, String due, String agriculture, String crops, String cropsDetail) {

        Log.e("addNoticeCard","addNoticeCard 입장!!!!!!!!!!!!!!!!!!");

        this.card = (LinearLayout) layoutInflater.inflate(R.layout.card_work_notice, layout, false);

        this.card.setOnClickListener(v -> {
            Intent intent = new Intent(getBaseContext(), NoticeDetailActivity.class);
            intent.putExtra("workId", workId);
            startActivity(intent);
            overridePendingTransition(0, 0);
        });

        TextView addressTextView = card.findViewById(R.id.address);
        TextView titleTextView = card.findViewById(R.id.title);
        TextView dueTextView = card.findViewById(R.id.due);
        TextView cropsTextView = card.findViewById(R.id.crops);
        TextView cropsDetailTextView = card.findViewById(R.id.crops_detail);

        addressTextView.setText(address);
        titleTextView.setText(title);
        dueTextView.setText(due);
        cropsTextView.setText(crops);
        cropsDetailTextView.setText(cropsDetail);

        // CardView를 noticeList에 추가

        this.card.findViewById(R.id.bookmark).setOnClickListener(v -> {
            if(this.card.findViewById(R.id.bookmark).isSelected()) {
                Log.e("card", "press cancel!!");
                this.card.findViewById(R.id.bookmark).setSelected(false);
                this.card.findViewById(R.id.bookmark).setBackground(getResources().getDrawable(R.drawable.svg_bookmark, null));
            } else {
                Log.e("card", "press !!");
                this.card.findViewById(R.id.bookmark).setSelected(true);
                this.card.findViewById(R.id.bookmark).setBackground(getResources().getDrawable(R.drawable.svg_bookmark_select, null));
            }
        });

        noticeList.addView(card);
    }


    // 일자리 공고 전부 받아오기
    private void workList(Long userId) {
        Log.e("workHome", "get workHome start!!");
        Call<ApiResponse> call = MyRetrofit.getApiService().workList(userId);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                Log.e("workHome", "workHomeList : ");
                Log.e("workHome", "workHomeList : " + response.body());
                ObjectMapper mapper = new ObjectMapper();
                String body = response.body().getData().toString();
                String json = body.substring(1, body.length()-1).replace("\\", "");
                Log.e("workHome", " body -> " + body);
                Log.e("workHome", " json -> " + json);
                try {
                    List<WorkDTO> dtos = Arrays.asList(mapper.readValue(json, WorkDTO[].class));

                    noticeList.removeAllViews();

                    // Loop through the WorkListDTOs and add them to the card views
                    for (WorkDTO dto : dtos) {
                        addNoticeCard(dto.getWorkId(), dto.getAddress(), dto.getTitle(), dto.getRecruitmentStart() + "~" + dto.getRecruitmentEnd(), dto.getAgriculture(), dto.getCrops(), dto.getCropsDetail());
                    }
                } catch (Exception e1) {
                    Log.e("workHome", "Error parsing JSON", e1);
                    e1.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.e("workHome failed", t.getMessage() + "");
            }
        });
    }

    // 일자리 필터링 (지역)
    private void workListAddress(String address, Long userId) {
        Log.e("workListAddress", "get workListAddress start!!");
        Call<ApiResponse> call = MyRetrofit.getApiService().workListAddress(address, userId);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                Log.e("workListAddress", "workListAddress : ");
                Log.e("workListAddress", "workListAddress : " + response.body());
                ObjectMapper mapper = new ObjectMapper();
                String body = response.body().getData().toString();
                String json = body.substring(1,body.length()-1).replace("\\", "");
                Log.e("workListAddress", " body -> " + body);
                Log.e("workListAddress", " json -> " + json);
                try {
//                List<BoardDTO> dtos = mapper.readValue(json, BoardDTO[].class);
                    List<WorkDTO> dtos = Arrays.asList(mapper.readValue(json, WorkDTO[].class));

                    noticeList.removeAllViews();
                    for (WorkDTO dto : dtos) {
                        addNoticeCard(dto.getWorkId(), dto.getAddress(), dto.getTitle(), dto.getRecruitmentStart() + "~" + dto.getRecruitmentEnd(), dto.getAgriculture(), dto.getCrops(), dto.getCropsDetail());
                    }

                } catch (Exception e1) {e1.printStackTrace();}

//                List<UserDTO> dtos = Arrays.asList(mapper.readValue(strList, UserDTO[].class));
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t)  {Log.e("workHome failed", t.getMessage() + "");}
        });
    }

    // 일자리 필터링 (농업구분)
    private void workListAgriculture(String agriculture, Long userId) {
        Log.e("workListAgriculture", "get workListAgriculture start!!");
        Call<ApiResponse> call = MyRetrofit.getApiService().workListAgriculture(agriculture, userId);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                Log.e("workListAgriculture", "workListAgriculture : ");
                Log.e("workListAgriculture", "workListAgriculture : " + response.body());
                ObjectMapper mapper = new ObjectMapper();
                String body = response.body().getData().toString();
                String json = body.substring(1, body.length()-1).replace("\\", "");
                Log.e("workListAgriculture", " body -> " + body);
                Log.e("workListAgriculture", " json -> " + json);
                try {
//                List<BoardDTO> dtos = mapper.readValue(json, BoardDTO[].class);
                    List<WorkDTO> dtos = Arrays.asList(mapper.readValue(json, WorkDTO[].class));

                    noticeList.removeAllViews();
                    for (WorkDTO dto : dtos) {
                        addNoticeCard(dto.getWorkId(), dto.getAddress(), dto.getTitle(), dto.getRecruitmentStart() + "~" + dto.getRecruitmentEnd(), dto.getAgriculture(), dto.getCrops(), dto.getCropsDetail());                    }

                } catch (Exception e1) {e1.printStackTrace();}

//                List<UserDTO> dtos = Arrays.asList(mapper.readValue(strList, UserDTO[].class));
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t)  {Log.e("workHome failed", t.getMessage() + "");}
        });
    }

    // 일자리 필터링 (작목)
    private void workListCrops(String crops, Long userId) {
        Log.e("workListCrops", "get workListCrops start!!");
        Call<ApiResponse> call = MyRetrofit.getApiService().workListCrops(crops, userId);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                Log.e("workListCrops", "workListCrops : ");
                Log.e("workListCrops", "workListCrops : " + response.body());
                ObjectMapper mapper = new ObjectMapper();
                String body = response.body().getData().toString();
                String json = body.substring(1, body.length()-1).replace("\\", "");
                Log.e("workListCrops", " body -> " + body);
                Log.e("workListCrops", " json -> " + json);
                try {
//                List<BoardDTO> dtos = mapper.readValue(json, BoardDTO[].class);
                    List<WorkDTO> dtos = Arrays.asList(mapper.readValue(json, WorkDTO[].class));

                    noticeList.removeAllViews();
                    for (WorkDTO dto : dtos) {
                        addNoticeCard(dto.getWorkId(), dto.getAddress(), dto.getTitle(), dto.getRecruitmentStart() + "~" + dto.getRecruitmentEnd(), dto.getAgriculture(), dto.getCrops(), dto.getCropsDetail());
                    }

                } catch (Exception e1) {e1.printStackTrace();}

//                List<UserDTO> dtos = Arrays.asList(mapper.readValue(strList, UserDTO[].class));
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t)  {Log.e("workHome failed", t.getMessage() + "");}
        });
    }

    // 일자리 필터링 (경력)
    private void workListCareer(float career, Long userId) {
        Log.e("workListCareer", "get workListCareer start!!");
        Call<ApiResponse> call = MyRetrofit.getApiService().workListCareer(career, userId);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                Log.e("workListCareer", "workListCareer : ");
                Log.e("workListCareer", "workListCareer : " + response.body());
                ObjectMapper mapper = new ObjectMapper();
                String body = response.body().getData().toString();
                String json = body.substring(1, body.length()-1).replace("\\", "");
                Log.e("workListCareer", " body -> " + body);
                Log.e("workListCareer", " json -> " + json);
                try {
//                List<BoardDTO> dtos = mapper.readValue(json, BoardDTO[].class);
                    List<WorkDTO> dtos = Arrays.asList(mapper.readValue(json, WorkDTO[].class));

                    noticeList.removeAllViews();
                    for (WorkDTO dto : dtos) {
                        addNoticeCard(dto.getWorkId(), dto.getAddress(), dto.getTitle(), dto.getRecruitmentStart() + "~" + dto.getRecruitmentEnd(), dto.getAgriculture(), dto.getCrops(), dto.getCropsDetail());
                    }

                } catch (Exception e1) {e1.printStackTrace();}

//                List<UserDTO> dtos = Arrays.asList(mapper.readValue(strList, UserDTO[].class));
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t)  {Log.e("workHome failed", t.getMessage() + "");}
        });
    }

    // 일자리 즐겨찾기
    private void userWorkList() {
        Log.e("userWorkList", "get userWorkList start!!");
        Call<ApiResponse> call = MyRetrofit.getApiService().userWorkList();
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                Log.e("userWorkList", "userWorkList : ");
                Log.e("userWorkList", "userWorkList : " + response.body());
                ObjectMapper mapper = new ObjectMapper();
                String body = response.body().getData().toString();
                String json = body.substring(1, body.length()-1).replace("\\", "");
                Log.e("userWorkList", " body -> " + body);
                Log.e("userWorkList", " json -> " + json);
                try {
//                List<BoardDTO> dtos = mapper.readValue(json, BoardDTO[].class);
                    List<WorkDTO> dtos = Arrays.asList(mapper.readValue(json, WorkDTO[].class));

                } catch (Exception e1) {e1.printStackTrace();}

//                List<UserDTO> dtos = Arrays.asList(mapper.readValue(strList, UserDTO[].class));
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t)  {Log.e("workHome failed", t.getMessage() + "");}
        });
    }

}