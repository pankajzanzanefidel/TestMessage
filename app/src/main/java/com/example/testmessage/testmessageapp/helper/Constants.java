package com.example.testmessage.testmessageapp.helper;

public class Constants {


    public static final class DatabaseConstants {

        public static final String DATABASE_NAME = "TEST_MESSAGE";

        public static final class CONTACTS {

            public static final String TABLE_NAME_CONTACT = "CONTACT";


            //public static final String COLUMN_NAME_CONATACTID = "DRIVERID";

            public static final String COLUMN_NAME_CONATACT_NUMBER = "NUMBER";
            public static final String COLUMN_NAME_CONATACT_NAME = "NAME";
            public static final String COLUMN_NAME_CONATACT_SURNAME = "SURNAME";

        }

        public static final class MESSAGE {

            public static final String TABLE_NAME_MESSAGE = "MESSAGE";
            public static final String COLUMN_NAME_MESSAGE_ID = "ID";
            public static final String COLUMN_NAME_MESSAGE_TEXT = "TEXT";
            public static final String COLUMN_NAME_MESSAGE_NUMBERS = "NUMBERS";
            public static final String COLUMN_NAME_MESSAGE_STATE= "STATE";
            public static final String COLUMN_NAME_JOB_ID= "JOB_ID";

        }
    }

    public static final class SHARED_PREFERNCE {

        public static final String PREFERENCE_SMS_PERRMISION = "PREFERENCE_SMS_PERRMISION";
        public static final String PREFERENCE_EXTERNAL_STORAGE_PERMISSION = "PREFERENCE_EXTERNAL_STORAGE_PERMISSION";

    }
    public static final class NOTIFICATION {
        public static final String NOTIFICATION_CHANNEL_GLOBAL = "NOTIFICATION_CHANNEL_GLOBAL";

        public static final int NOTIFICATION_ID_SERVICESAVE_FORGROUND = 1000;
        public static final int NOTIFICATION_ID_PUSH_DATA = 1001;
    }

    public static  final class REQUESTCODE {
        public static final int REQUEST_ALL_PERMISSIONS = 102;
    }
}