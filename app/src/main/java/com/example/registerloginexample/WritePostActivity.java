package com.example.registerloginexample;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.maps.GoogleMap;

public class WritePostActivity extends MainActivity {
    public static ImageView imageView1_test;
    private ArrayAdapter<CharSequence> adapter_city, adapter_sigungu;
    String choice_city = "";
    String choice_sigungu = "";

    public static ImageView getImageView1_test() {
        return imageView1_test;
    }

    public void replaceFragment(FragTuesday fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    public static void setImageView1_test(ImageView imageView1_tests) {
        imageView1_test = imageView1_tests;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_post);

        imageView1_test = (ImageView) findViewById(R.id.imageView);
        findViewById(R.id.check_button).setOnClickListener(onClickListener);
        findViewById(R.id.cancel_button).setOnClickListener(onClickListener);
        final Spinner spinnerCity = (Spinner) findViewById(R.id.spinner_city);
        final Spinner spinnerSigungu = (Spinner) findViewById(R.id.spinner_sigungu);

        //imageView1_test.setImageResource(R.drawable.pass_currect);
        GoogleMap.SnapshotReadyCallback callback = new GoogleMap.SnapshotReadyCallback() {
            @Override
            public void onSnapshotReady(Bitmap snapshot) {
                imageView1_test.setImageBitmap(snapshot);
            }
        };




        adapter_city = ArrayAdapter.createFromResource(WritePostActivity.this, R.array.spinner_region,
                                                        android.R.layout.simple_spinner_dropdown_item);
        adapter_city.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCity.setAdapter(adapter_city);
        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                if (adapter_city.getItem(position).equals("서울특별시")) {
                    choice_city = "서울특별시";
                    adapter_sigungu = ArrayAdapter.createFromResource(WritePostActivity.this, R.array.spinner_region_seoul,
                                                                        android.R.layout.simple_spinner_dropdown_item);
                    adapter_sigungu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerSigungu.setAdapter(adapter_sigungu);
                    spinnerSigungu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                            choice_sigungu = adapter_sigungu.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView1) {
                        }
                    });
                }else if (adapter_city.getItem(position).equals("부산광역시")) {
                    choice_city = "부산광역시";
                    adapter_sigungu = ArrayAdapter.createFromResource(WritePostActivity.this, R.array.spinner_region_busan, android.R.layout.simple_spinner_dropdown_item);
                    adapter_sigungu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerSigungu.setAdapter(adapter_sigungu);
                    spinnerSigungu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                            choice_sigungu = adapter_sigungu.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView1) {
                        }
                    });
                }else if (adapter_city.getItem(position).equals("대구광역시")) {
                    choice_city = "대구광역시";
                    adapter_sigungu = ArrayAdapter.createFromResource(WritePostActivity.this, R.array.spinner_region_daegu, android.R.layout.simple_spinner_dropdown_item);
                    adapter_sigungu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerSigungu.setAdapter(adapter_sigungu);
                    spinnerSigungu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                            choice_sigungu = adapter_sigungu.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView1) {
                        }
                    });
                }else if (adapter_city.getItem(position).equals("인천광역시")) {
                    choice_city = "인천광역시";
                    adapter_sigungu = ArrayAdapter.createFromResource(WritePostActivity.this, R.array.spinner_region_incheon, android.R.layout.simple_spinner_dropdown_item);
                    adapter_sigungu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerSigungu.setAdapter(adapter_sigungu);
                    spinnerSigungu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                            choice_sigungu = adapter_sigungu.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView1) {
                        }
                    });
                }else if (adapter_city.getItem(position).equals("광주광역시")) {
                    choice_city = "광주광역시";
                    adapter_sigungu = ArrayAdapter.createFromResource(WritePostActivity.this, R.array.spinner_region_gwangju, android.R.layout.simple_spinner_dropdown_item);
                    adapter_sigungu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerSigungu.setAdapter(adapter_sigungu);
                    spinnerSigungu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                            choice_sigungu = adapter_sigungu.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView1) {
                        }
                    });
                }else if (adapter_city.getItem(position).equals("대전광역시")) {
                    choice_city = "대전광역시";
                    adapter_sigungu = ArrayAdapter.createFromResource(WritePostActivity.this, R.array.spinner_region_daejeon, android.R.layout.simple_spinner_dropdown_item);
                    adapter_sigungu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerSigungu.setAdapter(adapter_sigungu);
                    spinnerSigungu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                            choice_sigungu = adapter_sigungu.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView1) {
                        }
                    });
                }else if (adapter_city.getItem(position).equals("울산광역시")) {
                    choice_city = "울산광역시";
                    adapter_sigungu = ArrayAdapter.createFromResource(WritePostActivity.this, R.array.spinner_region_ulsan, android.R.layout.simple_spinner_dropdown_item);
                    adapter_sigungu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerSigungu.setAdapter(adapter_sigungu);
                    spinnerSigungu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                            choice_sigungu = adapter_sigungu.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView1) {
                        }
                    });
                }else if (adapter_city.getItem(position).equals("세종특별자치시")) {
                    choice_city = "세종특별자치시";
                    adapter_sigungu = ArrayAdapter.createFromResource(WritePostActivity.this, R.array.spinner_region_sejong, android.R.layout.simple_spinner_dropdown_item);
                    adapter_sigungu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerSigungu.setAdapter(adapter_sigungu);
                    spinnerSigungu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                            choice_sigungu = adapter_sigungu.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView1) {
                        }
                    });
                }else if (adapter_city.getItem(position).equals("경기도")) {
                    choice_city = "경기도";
                    adapter_sigungu = ArrayAdapter.createFromResource(WritePostActivity.this, R.array.spinner_region_gyeonggi, android.R.layout.simple_spinner_dropdown_item);
                    adapter_sigungu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerSigungu.setAdapter(adapter_sigungu);
                    spinnerSigungu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                            choice_sigungu = adapter_sigungu.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView1) {
                        }
                    });
                }else if (adapter_city.getItem(position).equals("강원도")) {
                    choice_city = "강원도";
                    adapter_sigungu = ArrayAdapter.createFromResource(WritePostActivity.this, R.array.spinner_region_gangwon, android.R.layout.simple_spinner_dropdown_item);
                    adapter_sigungu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerSigungu.setAdapter(adapter_sigungu);
                    spinnerSigungu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                            choice_sigungu = adapter_sigungu.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView1) {
                        }
                    });
                }else if (adapter_city.getItem(position).equals("충청북도")) {
                    choice_city = "충청북도";
                    adapter_sigungu = ArrayAdapter.createFromResource(WritePostActivity.this, R.array.spinner_region_chung_buk, android.R.layout.simple_spinner_dropdown_item);
                    adapter_sigungu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerSigungu.setAdapter(adapter_sigungu);
                    spinnerSigungu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                            choice_sigungu = adapter_sigungu.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView1) {
                        }
                    });
                }else if (adapter_city.getItem(position).equals("충청남도")) {
                    choice_city = "충청남도";
                    adapter_sigungu = ArrayAdapter.createFromResource(WritePostActivity.this, R.array.spinner_region_chung_nam, android.R.layout.simple_spinner_dropdown_item);
                    adapter_sigungu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerSigungu.setAdapter(adapter_sigungu);
                    spinnerSigungu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                            choice_sigungu = adapter_sigungu.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView1) {
                        }
                    });
                }else if (adapter_city.getItem(position).equals("전라북도")) {
                    choice_city = "전라북도";
                    adapter_sigungu = ArrayAdapter.createFromResource(WritePostActivity.this, R.array.spinner_region_jeon_buk, android.R.layout.simple_spinner_dropdown_item);
                    adapter_sigungu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerSigungu.setAdapter(adapter_sigungu);
                    spinnerSigungu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                            choice_sigungu = adapter_sigungu.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView1) {
                        }
                    });
                }else if (adapter_city.getItem(position).equals("전라남도")) {
                    choice_city = "전라남도";
                    adapter_sigungu = ArrayAdapter.createFromResource(WritePostActivity.this, R.array.spinner_region_jeon_nam, android.R.layout.simple_spinner_dropdown_item);
                    adapter_sigungu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerSigungu.setAdapter(adapter_sigungu);
                    spinnerSigungu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                            choice_sigungu = adapter_sigungu.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView1) {
                        }
                    });
                }else if (adapter_city.getItem(position).equals("경상북도")) {
                    choice_city = "경상북도";
                    adapter_sigungu = ArrayAdapter.createFromResource(WritePostActivity.this, R.array.spinner_region_gyeong_buk, android.R.layout.simple_spinner_dropdown_item);
                    adapter_sigungu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerSigungu.setAdapter(adapter_sigungu);
                    spinnerSigungu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                            choice_sigungu = adapter_sigungu.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView1) {
                        }
                    });
                }else if (adapter_city.getItem(position).equals("경상남도")) {
                    choice_city = "경상남도";
                    adapter_sigungu = ArrayAdapter.createFromResource(WritePostActivity.this, R.array.spinner_region_gyeong_nam, android.R.layout.simple_spinner_dropdown_item);
                    adapter_sigungu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerSigungu.setAdapter(adapter_sigungu);
                    spinnerSigungu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                            choice_sigungu = adapter_sigungu.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView1) {
                        }
                    });
                }else if (adapter_city.getItem(position).equals("제주특별자치도")) {
                    choice_city = "제주특별자치도";
                    adapter_sigungu = ArrayAdapter.createFromResource(WritePostActivity.this, R.array.spinner_region_jeju, android.R.layout.simple_spinner_dropdown_item);
                    adapter_sigungu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerSigungu.setAdapter(adapter_sigungu);
                    spinnerSigungu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                            choice_sigungu = adapter_sigungu.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView1) {
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView1) {
            }
        });
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.check_button:

                    break;
                case R.id.cancel_button:
                    finish();
                    break;
            }
        }
    };
}