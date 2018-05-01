package ztml.dev.ngokhacbac.autotool;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import ztml.dev.ngokhacbac.autotool.model.ServiceListerServer;
import ztml.dev.ngokhacbac.autotool.model.SmsReceiver;
import ztml.dev.ngokhacbac.autotool.presenter.Mainpresneter;

public class MainActivity extends AppCompatActivity {
    private Mainpresneter mainpresneter;

    public static final String OTP_REGEX = "[0-9]{1,6}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainpresneter = new Mainpresneter(this);
        mainpresneter.registerThaoLap(this);
        mainpresneter.registerUSSD(this);
        checkCallPermission();
        mainpresneter.registerSMS(this);

        Intent intent = new Intent(this, ServiceListerServer.class);
        startService(intent);

        Button button = (Button) findViewById(R.id.buttonCall);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if (Build.VERSION.SDK_INT >= 23) {
                    checkCallPermission();
                } else {
                    // Toast.makeText(MainActivity.this, "Device is below 6.0 do your work", Toast.LENGTH_LONG).show();
                }
                mainpresneter.setFirstStart(getApplicationContext(), true);
            }
        });
    }

    private void checkCallPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CALL_PHONE)) {
                /**
                 *
                 */
                new AlertDialog.Builder(this)
                        .setTitle("Permission Required")
                        .setMessage("This permission was denied earlier by you. This permission is required to call from app .So, in order to use this feature please allow this permission by clicking ok.")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                ActivityCompat.requestPermissions(MainActivity.this,
                                        new String[]{Manifest.permission.CALL_PHONE},
                                        0);
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CALL_PHONE}, 2);
            }
        } else {
            Toast.makeText(this, "Permission Aleardy granted", Toast.LENGTH_LONG).show();
        }
        /***********************************/
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_SMS)) {
                /**
                 *
                 */
                new AlertDialog.Builder(this)
                        .setTitle("Permission Required")
                        .setMessage("This permission was denied earlier by you. This permission is required to call from app .So, in order to use this feature please allow this permission by clicking ok.")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                ActivityCompat.requestPermissions(MainActivity.this,
                                        new String[]{Manifest.permission.READ_SMS},
                                        1);
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_SMS}, 2);
            }
        } else {
            Toast.makeText(this, "Permission Aleardy granted", Toast.LENGTH_LONG).show();
        }
        /***********************************/
        /***********************************/
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_PHONE_STATE)) {
                /**
                 *
                 */
                new AlertDialog.Builder(this)
                        .setTitle("Permission Required")
                        .setMessage("This permission was denied earlier by you. This permission is required to call from app .So, in order to use this feature please allow this permission by clicking ok.")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                ActivityCompat.requestPermissions(MainActivity.this,
                                        new String[]{Manifest.permission.READ_PHONE_STATE},
                                        3);
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_PHONE_STATE}, 2);
            }
        } else {
            Toast.makeText(this, "Permission Aleardy granted", Toast.LENGTH_LONG).show();
        }
        /***********************************/
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PRIVILEGED)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CALL_PRIVILEGED)) {
                /**
                 *
                 */
                new AlertDialog.Builder(this)
                        .setTitle("Permission Required")
                        .setMessage("This permission was denied earlier by you. This permission is required to call from app .So, in order to use this feature please allow this permission by clicking ok.")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                ActivityCompat.requestPermissions(MainActivity.this,
                                        new String[]{Manifest.permission.CALL_PRIVILEGED},
                                        4);
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CALL_PRIVILEGED}, 2);
            }
        } else {
            Toast.makeText(this, "Permission Aleardy granted", Toast.LENGTH_LONG).show();
        }
//        /***********************************/
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.PROCESS_OUTGOING_CALLS)
//                != PackageManager.PERMISSION_GRANTED) {
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
//                    Manifest.permission.PROCESS_OUTGOING_CALLS)) {
//                /**
//                 *
//                 */
//                new AlertDialog.Builder(this)
//                        .setTitle("Permission Required")
//                        .setMessage("This permission was denied earlier by you. This permission is required to call from app .So, in order to use this feature please allow this permission by clicking ok.")
//                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.dismiss();
//                                ActivityCompat.requestPermissions(MainActivity.this,
//                                        new String[]{Manifest.permission.PROCESS_OUTGOING_CALLS},
//                                        5);
//                            }
//                        })
//                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.dismiss();
//                            }
//                        })
//                        .setIcon(android.R.drawable.ic_dialog_alert)
//                        .show();
//
//            } else {
//                ActivityCompat.requestPermissions(this,
//                        new String[]{Manifest.permission.PROCESS_OUTGOING_CALLS}, 2);
//            }
//        } else {
//            Toast.makeText(this, "Permission Aleardy granted", Toast.LENGTH_LONG).show();
//        }
//        /***********************************/
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED) {
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
//                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//                /**
//                 *
//                 */
//                new AlertDialog.Builder(this)
//                        .setTitle("Permission Required")
//                        .setMessage("This permission was denied earlier by you. This permission is required to call from app .So, in order to use this feature please allow this permission by clicking ok.")
//                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.dismiss();
//                                ActivityCompat.requestPermissions(MainActivity.this,
//                                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
//                                        2);
//                            }
//                        })
//                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.dismiss();
//                            }
//                        })
//                        .setIcon(android.R.drawable.ic_dialog_alert)
//                        .show();
//
//            } else {
//                ActivityCompat.requestPermissions(this,
//                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
//            }
//        } else {
//            Toast.makeText(this, "Permission Aleardy granted", Toast.LENGTH_LONG).show();
//        }
    }

    /**
     * This method will be invoked when user allows or deny's a permission from the permission dialog so take actions accordingly.
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 0:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, Do the Location -related task you need to do.
                    Toast.makeText(this, "COARSE Permission granted", Toast.LENGTH_LONG).show();
                } else {
                    // This block will be called when user has denied the permission. There will be two possibilities.
                    //1. User has simple denied the permission by clicking on the deny button the permission dialog.
                    //2. User has ticked the never show dialog and denied the permission from the permission dialog.
                    String permission = permissions[0];
                    boolean showRationale = ActivityCompat.shouldShowRequestPermissionRationale(this, permission);
                    if (!showRationale) {
                        //We will be in this block when User has ticked the never show dialog and denied the permission from the permission dialog
                        //Here we can not request the permission again if user has denied the permission with never ask option enabled.
                        //Only way is to show a imfo dialog and ask user to grant the permission from settings.
                        Toast.makeText(this, "COARSE Permission Denied with never show options", Toast.LENGTH_LONG).show();
                    } else {
                        //We will be in this block when User has denied the permission from the permission dialog and not clicked on the never show again checkbox.
                        //We can show a explaination why we need this permission and request permission again.
                        Toast.makeText(this, "COARSE Permission Denied", Toast.LENGTH_LONG).show();
                        //permission denied,Disable the functionality that depends on this permission.
                    }

                }
                break;
            case 1:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Call Permission granted", Toast.LENGTH_LONG).show();
                } else {
                    String permission = permissions[0];
                    boolean showRationale = ActivityCompat.shouldShowRequestPermissionRationale(this, permission);
                    if (!showRationale) {
                        Toast.makeText(this, "Call Permission Denied with never show options", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this, "Call Permission Denied", Toast.LENGTH_LONG).show();
                    }
                    break;
                }
                break;
            case 2:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "RECORD_AUDIO Permission granted", Toast.LENGTH_LONG).show();
                } else {
                    String permission = permissions[0];
                    boolean showRationale = ActivityCompat.shouldShowRequestPermissionRationale(this, permission);
                    if (!showRationale) {
                        Toast.makeText(this, "RECORD_AUDIO Permission Denied with never show options", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this, "RECORD_AUDIO Permission Denied", Toast.LENGTH_LONG).show();
                    }
                    break;
                }
                break;
            case 3:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "READ_PHONE_STATE Permission granted", Toast.LENGTH_LONG).show();
                } else {
                    String permission = permissions[0];
                    boolean showRationale = ActivityCompat.shouldShowRequestPermissionRationale(this, permission);
                    if (!showRationale) {
                        Toast.makeText(this, "READ_PHONE_STATE Permission Denied with never show options", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this, "READ_PHONE_STATE Permission Denied", Toast.LENGTH_LONG).show();
                    }
                    break;
                }
                break;
            case 4:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "CALL_PRIVILEGED Permission granted", Toast.LENGTH_LONG).show();
                } else {
                    String permission = permissions[0];
                    boolean showRationale = ActivityCompat.shouldShowRequestPermissionRationale(this, permission);
                    if (!showRationale) {
                        Toast.makeText(this, "CALL_PRIVILEGED Permission Denied with never show options", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this, "CALL_PRIVILEGED Permission Denied", Toast.LENGTH_LONG).show();
                    }
                    break;
                }
                break;
//            case 5:
//                if (grantResults.length > 0
//                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    Toast.makeText(this, "PROCESS_OUTGOING_CALLS Permission granted", Toast.LENGTH_LONG).show();
//                } else {
//                    String permission = permissions[0];
//                    boolean showRationale = ActivityCompat.shouldShowRequestPermissionRationale(this, permission);
//                    if (!showRationale) {
//                        Toast.makeText(this, "PROCESS_OUTGOING_CALLS Permission Denied with never show options", Toast.LENGTH_LONG).show();
//                    } else {
//                        Toast.makeText(this, "PROCESS_OUTGOING_CALLS Permission Denied", Toast.LENGTH_LONG).show();
//                    }
//                    break;
//                }
//                break;
        }
    }
}