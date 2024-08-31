package com.abc.restaurant.constant;
import org.springframework.stereotype.Component;

@Component
public class EmailTemplate {

    public static final String SEND_USER_PASSWORD_RESET_OTP_EMAIL_SUBJECT = "Use this OTP to reset your password.";

    public String sendUserOTPTemplate(int OTP) {
        return "<!DOCTYPE html>\n" +
                "\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <title></title>\n" +
                "    <meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\"/>\n" +
                "    <meta content=\"width=device-width, initial-scale=1.0\" name=\"viewport\"/>\n" +
                "    <!--[if mso]>\n" +
                "    <xml>\n" +
                "        <o:OfficeDocumentSettings>\n" +
                "            <o:PixelsPerInch>96</o:PixelsPerInch>\n" +
                "            <o:AllowPNG/>\n" +
                "        </o:OfficeDocumentSettings>\n" +
                "    </xml><![endif]--><!--[if !mso]><!-->\n" +
                "    <link href=\"https://fonts.googleapis.com/css?family=Noto+Serif\" rel=\"stylesheet\" type=\"text/css\"/>\n" +
                "    <link href=\"https://fonts.googleapis.com/css2?family=Inter&family=Work+Sans:wght@700&display=swap\" rel=\"stylesheet\"\n" +
                "          type=\"text/css\"/>\n" +
                "    <link href=\"https://fonts.googleapis.com/css2?family=Roboto:wght@100;200;300;400;500;600;700;800;900\"\n" +
                "          rel=\"stylesheet\" type=\"text/css\"/><!--<![endif]-->\n" +
                "\t<link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\n" +
                "\t<link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\n" +
                "\t<link href=\"https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500&display=swap\" rel=\"stylesheet\">\n" +
                "    <style>\n" +
                "        * {\n" +
                "            box-sizing: border-box;\n" +
                "        }\n" +
                "\n" +
                "        body {\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "        }\n" +
                "\n" +
                "        a[x-apple-data-detectors] {\n" +
                "            color: inherit !important;\n" +
                "            text-decoration: inherit !important;\n" +
                "        }\n" +
                "\n" +
                "        #MessageViewBody a {\n" +
                "            color: inherit;\n" +
                "            text-decoration: none;\n" +
                "        }\n" +
                "\n" +
                "        p {\n" +
                "            line-height: inherit\n" +
                "        }\n" +
                "\n" +
                "        .desktop_hide,\n" +
                "        .desktop_hide table {\n" +
                "            mso-hide: all;\n" +
                "            display: none;\n" +
                "            max-height: 0px;\n" +
                "            overflow: hidden;\n" +
                "        }\n" +
                "\n" +
                "        .image_block img + div {\n" +
                "            display: none;\n" +
                "        }\n" +
                "\n" +
                "        @media (max-width: 620px) {\n" +
                "            .desktop_hide table.icons-inner {\n" +
                "                display: inline-block !important;\n" +
                "            }\n" +
                "\n" +
                "            .icons-inner {\n" +
                "                text-align: center;\n" +
                "            }\n" +
                "\n" +
                "            .icons-inner td {\n" +
                "                margin: 0 auto;\n" +
                "            }\n" +
                "\n" +
                "            .mobile_hide {\n" +
                "                display: none;\n" +
                "            }\n" +
                "\n" +
                "            .row-content {\n" +
                "                width: 100% !important;\n" +
                "            }\n" +
                "\n" +
                "            .stack .column {\n" +
                "                width: 100%;\n" +
                "                display: block;\n" +
                "            }\n" +
                "\n" +
                "            .mobile_hide {\n" +
                "                min-height: 0;\n" +
                "                max-height: 0;\n" +
                "                max-width: 0;\n" +
                "                overflow: hidden;\n" +
                "                font-size: 0px;\n" +
                "            }\n" +
                "\n" +
                "            .desktop_hide,\n" +
                "            .desktop_hide table {\n" +
                "                display: table !important;\n" +
                "                max-height: none !important;\n" +
                "            }\n" +
                "\n" +
                "            .row-4 .column-1 {\n" +
                "                padding: 5px 25px !important;\n" +
                "            }\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body style=\"margin: 0; background-color: #fff; padding: 0; -webkit-text-size-adjust: none; text-size-adjust: none;\">\n" +
                "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"nl-container\" role=\"presentation\"\n" +
                "       style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #fff;\" width=\"100%\">\n" +
                "    <tbody>\n" +
                "    <tr>\n" +
                "        <td>\n" +
                "            <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-1\" role=\"presentation\"\n" +
                "                   style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #ede9e9;\" width=\"100%\">\n" +
                "                <tbody>\n" +
                "                <tr>\n" +
                "                    <td>\n" +
                "                        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\"\n" +
                "                               role=\"presentation\"\n" +
                "                               style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #fff; border-radius: 0; color: #000; border-bottom: 0 solid #efeef4; border-left: 0 solid #efeef4; border-right: 0px solid #efeef4; border-top: 0 solid #efeef4; width: 600px; margin: 0 auto;\"\n" +
                "                               width=\"600\">\n" +
                "                            <tbody>\n" +
                "                            <tr>\n" +
                "                                <td class=\"column column-1\"\n" +
                "                                    style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; background-color: #ffffff; border-bottom: 1px solid transparent; border-left: 1px solid transparent; border-right: 1px solid transparent; border-top: 1px solid transparent; padding-top: 10px; vertical-align: top;\"\n" +
                "                                    width=\"100%\">\n" +
                "                                    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"icons_block block-1\"\n" +
                "                                           role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\"\n" +
                "                                           width=\"100%\">\n" +
                "                                        <tr>\n" +
                "                                            <td class=\"pad\"\n" +
                "                                                style=\"vertical-align: middle; color: #4f5aba; font-family: 'Noto Serif', Georgia, serif; font-size: 24px; letter-spacing: 0px; padding-bottom: 10px; padding-top: 10px; text-align: center;\">\n" +
                "                                                <table align=\"center\" cellpadding=\"0\" cellspacing=\"0\" class=\"alignment\"\n" +
                "                                                       role=\"presentation\"\n" +
                "                                                       style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
                "                                                    <tr>\n" +
                "                                                        <td style=\"vertical-align: middle; text-align: center; padding-top: 10px; padding-bottom: 0px; padding-left: 20px; padding-right: 20px;\">\n" +
                "                                                            <a href=\"https://www.example.com\"\n" +
                "                                                               style=\"text-decoration: none;\" target=\"_self\"><img\n" +
                "                                                                    align=\"center\" alt=\"new year celebration\"\n" +
                "                                                                    class=\"icon\" height=\"64\"\n" +
                "                                                                    src=\"https://imagesofspicymeals.s3.ap-south-1.amazonaws.com/upload/logo.png\"\n" +
                "                                                                    style=\"display: block; height: auto; margin: 0 auto; border: 0;\"\n" +
                "                                                                    width=\"49\"/></a></td>\n" +
                "                                                    </tr>\n" +
                "                                                </table>\n" +
                "                                            </td>\n" +
                "                                        </tr>\n" +
                "                                    </table>\n" +
                "                                    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"heading_block block-2\"\n" +
                "                                           role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\"\n" +
                "                                           width=\"100%\">\n" +
                "                                        <tr>\n" +
                "                                            <td class=\"pad\" style=\"padding-top:10px;text-align:center;width:100%;\">\n" +
                "                                                <h1 style=\"margin: 0; color: #1a2028; direction: ltr; font-family: 'Roboto', Tahoma, Verdana, Segoe, sans-serif; font-size: 36px; font-weight: 700; letter-spacing: 1px; line-height: 120%; text-align: center; margin-top: 0; margin-bottom: 0;\">\n" +
                "                                                    <span class=\"tinyMce-placeholder\">Spicy Meals Restaurant</span></h1>\n" +
                "                                            </td>\n" +
                "                                        </tr>\n" +
                "                                    </table>\n" +
                "                                    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"paragraph_block block-3\"\n" +
                "                                           role=\"presentation\"\n" +
                "                                           style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\"\n" +
                "                                           width=\"100%\">\n" +
                "                                        <tr>\n" +
                "                                            <td class=\"pad\"\n" +
                "                                                style=\"padding-bottom:10px;padding-left:10px;padding-right:10px;padding-top:20px;\">\n" +
                "                                                <div style=\"color:#101112;direction:ltr;font-family:Inter, sans-serif;font-size:14px;font-weight:400;letter-spacing:0px;line-height:120%;text-align:center;mso-line-height-alt:16.8px;\">\n" +
                "                                                    <p style=\"margin: 0; margin-bottom: 3px;\">Enter the validation code\n" +
                "                                                        we just sent</p>\n" +
                "                                                    <p style=\"margin: 0;\">you on your email address</p>\n" +
                "                                                </div>\n" +
                "                                            </td>\n" +
                "                                        </tr>\n" +
                "                                    </table>\n" +
                "                                    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"image_block block-4\"\n" +
                "                                           role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\"\n" +
                "                                           width=\"100%\">\n" +
                "                                        <tr>\n" +
                "                                            <td class=\"pad\" style=\"width:100%;padding-right:0px;padding-left:0px;\">\n" +
                "                                                <div align=\"center\" class=\"alignment\" style=\"line-height:10px\"><img\n" +
                "                                                        src=\"https://imagesofspicymeals.s3.ap-south-1.amazonaws.com/upload/pawd.png\"\n" +
                "                                                        style=\"display: block; height: auto; border: 0; max-width: 239px; width: 100%;\"\n" +
                "                                                        width=\"239\"/></div>\n" +
                "                                            </td>\n" +
                "                                        </tr>\n" +
                "                                    </table>\n" +
                "                                    <table border=\"0\" cellpadding=\"10\" cellspacing=\"0\" class=\"heading_block block-5\"\n" +
                "                                           role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\"\n" +
                "                                           width=\"100%\">\n" +
                "                                        <tr>\n" +
                "                                            <td class=\"pad\">\n" +
                "                                                <h3 style=\"margin: 0; color: #88b44e; direction: ltr; font-family: Inter, sans-serif; font-size: 14px; font-weight: 500; letter-spacing: normal; line-height: 150%; text-align: center; margin-top: 0; margin-bottom: 0;\">\n" +
//                "                                                    <img src=\"https://imagesofspicymeals.s3.ap-south-1.amazonaws.com/upload/lock+(1).png\" style=\"margin-bottom: -2px;\" /> Forget Password Pin</h3>\n" +
                "                                            </td>\n" +
                "                                        </tr>\n" +
                "                                    </table>\n" +
                "                                </td>\n" +
                "                            </tr>\n" +
                "                            </tbody>\n" +
                "                        </table>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "                </tbody>\n" +
                "            </table>\n" +
                "            <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-2\" role=\"presentation\"\n" +
                "                   style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #ede9e9; background-size: auto;\"\n" +
                "                   width=\"100%\">\n" +
                "                <tbody>\n" +
                "                <tr>\n" +
                "                    <td>\n" +
                "\n" +
                "\n" +
                "                        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-2\" role=\"presentation\"\n" +
                "       style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #ede9e9; background-size: auto;\"\n" +
                "       width=\"100%\">\n" +
                "    <tbody>\n" +
                "    <tr>\n" +
                "        <td>\n" +
                "            <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content\" role=\"presentation\"\n" +
                "                   style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #fff; background-size: auto; border-radius: 0; color: #000; width: 600px; margin: 0 auto;\" width=\"600\">\n" +
                "                <tbody>\n" +
                "                <tr>\n" +
                "                    <td class=\"column column-center\" colspan=\"4\"\n" +
                "                        style=\"mso-table-lspace: 0pt;  mso-table-rspace: 0pt; font-weight: 400; text-align: center; vertical-align: top; padding-bottom: 5px; padding-top: 5px;\">\n" +
                "                        <!-- Start of OTP Divs -->\n" +
                "                        <div style=\"display: flex; justify-content: center; gap: 12px !important; align-items: center; margin-left: auto; margin-right: auto; max-width: 220px;\">\n" +
                "                            <div class=\"otp-div\" style=\"border: 2px solid #88B44E; margin-left:4px !important; border-radius: 5px; padding: 15px; width: 50px;\">\n" +
                "                                <p style=\"color: #88b44e; font-family: Inter, sans-serif; font-size: 14px; font-weight: 700; letter-spacing: 0px; line-height: 120%; margin: 0;\">" + String.valueOf(OTP).charAt(0) + "</p>\n" +
                "                            </div>\n" +
                "                            <div class=\"otp-div\" style=\"border: 2px solid #88B44E; margin-left:4px !important; border-radius: 5px; padding: 15px; width: 50px;\">\n" +
                "                                <p style=\"color: #88b44e; font-family: Inter, sans-serif; font-size: 14px; font-weight: 700; letter-spacing: 0px; line-height: 120%; margin: 0;\">" + String.valueOf(OTP).charAt(1) + "</p>\n" +
                "                            </div>\n" +
                "                            <div class=\"otp-div\" style=\"border: 2px solid #88B44E; margin-left:4px !important; border-radius: 5px; padding: 15px; width: 50px;\">\n" +
                "                                <p style=\"color: #88b44e; font-family: Inter, sans-serif; font-size: 14px; font-weight: 700; letter-spacing: 0px; line-height: 120%; margin: 0;\">" + String.valueOf(OTP).charAt(2) + "</p>\n" +
                "                            </div>\n" +
                "                            <div class=\"otp-div\" style=\"border: 2px solid #88B44E; margin-left:4px !important; border-radius: 5px; padding: 15px; width: 50px;\">\n" +
                "                                <p style=\"color: #88b44e; font-family: Inter, sans-serif; font-size: 14px; font-weight: 700; letter-spacing: 0px; line-height: 120%; margin: 0;\">" + String.valueOf(OTP).charAt(3) + "</p>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                        <!-- End of OTP Divs -->\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "                </tbody>\n" +
                "            </table>\n" +
                "        </td>\n" +
                "    </tr>\n" +
                "    </tbody>\n" +
                "</table>\n" +
                "            <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-3\" role=\"presentation\"\n" +
                "                   style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #EDE9E9;\" width=\"100%\">\n" +
                "                <tbody>\n" +
                "                <tr>\n" +
                "                    <td>\n" +
                "                        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\"\n" +
                "                               role=\"presentation\"\n" +
                "                               style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #fff; border-radius: 0; color: #000; width: 600px; margin: 0 auto;\"\n" +
                "                               width=\"600\">\n" +
                "                            <tbody>\n" +
                "                            <tr>\n" +
                "                                <td class=\"column column-1\"\n" +
                "                                    style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\"\n" +
                "                                    width=\"100%\">\n" +
                "                                    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"paragraph_block block-1\"\n" +
                "                                           role=\"presentation\"\n" +
                "                                           style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\"\n" +
                "                                           width=\"100%\">\n" +
                "                                        <tr>\n" +
                "                                            <td class=\"pad\">\n" +
                "                                                <div style=\"color:#101112;direction:ltr;font-family:Inter, sans-serif;font-size:16px;font-weight:400;letter-spacing:0px;line-height:120%;text-align:left;mso-line-height-alt:19.2px;\">\n" +
                "                                                     \n" +
                "                                                </div>\n" +
                "                                            </td>\n" +
                "                                        </tr>\n" +
                "                                    </table>\n" +
                "                                </td>\n" +
                "                            </tr>\n" +
                "                            </tbody>\n" +
                "                        </table>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "                </tbody>\n" +
                "            </table>\n" +
                "            <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-4\" role=\"presentation\"\n" +
                "                   style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #ede9e9; background-size: auto;\"\n" +
                "                   width=\"100%\">\n" +
                "                <tbody>\n" +
                "                <tr>\n" +
                "                    <td>\n" +
                "                        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\"\n" +
                "                               role=\"presentation\"\n" +
                "                               style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-size: auto; border-radius: 0; color: #000; background-color: #88b44e; width: 600px; margin: 0 auto;\"\n" +
                "                               width=\"600\">\n" +
                "                            <tbody>\n" +
                "                            <tr>\n" +
                "                                <td class=\"column column-1\"\n" +
                "                                    style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 10px; padding-left: 10px; padding-right: 10px; padding-top: 10px; vertical-align: middle; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\"\n" +
                "                                    width=\"100%\">\n" +
                "                                    <table border=\"0\" cellpadding=\"5\" cellspacing=\"0\" class=\"paragraph_block block-1\"\n" +
                "                                           role=\"presentation\"\n" +
                "                                           style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\"\n" +
                "                                           width=\"100%\">\n" +
                "                                        <tr>\n" +
                "                                            <td class=\"pad\">\n" +
                "                                                <div style=\"color:#ffffff;direction:ltr;font-family:'Roboto', Tahoma, Verdana, Segoe, sans-serif;font-size:11px;font-weight:400;letter-spacing:0px;line-height:120%;text-align:center;mso-line-height-alt:13.2px;\">\n" +
                "                                                    <p style=\"margin: 0; margin-bottom: 16px;\">www.johnkeellstea.com</p>\n" +
                "                                                    <p style=\"margin: 0; margin-bottom: 16px;\">Finally House, 186 ,\n" +
                "                                                        Vauxhall Street, Colombo 2 , Srilanka</p>\n" +
                "                                                    <p style=\"margin: 0;\">+94 112 30 6000</p>\n" +
                "                                                </div>\n" +
                "                                            </td>\n" +
                "                                        </tr>\n" +
                "                                    </table>\n" +
                "                                </td>\n" +
                "                            </tr>\n" +
                "                            </tbody>\n" +
                "                        </table>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "                </tbody>\n" +
                "            </table>\n" +
                "            <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-5\" role=\"presentation\"\n" +
                "                   style=\"mso-table-lspace: 0pt; background-color: #EDE9E9; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                "                <tbody>\n" +
                "                <tr>\n" +
                "                    <td>\n" +
                "                        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"  class=\"row-content stack\"\n" +
                "                               role=\"presentation\"\n" +
                "                               style=\"mso-table-lspace: 0pt;  mso-table-rspace: 0pt; color: #000; width: 600px; margin: 0 auto;\"\n" +
                "                               width=\"600\">\n" +
                "                            <tbody>\n" +
                "                            <tr>\n" +
                "                                <td class=\"column column-1\"\n" +
                "                                    style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\"\n" +
                "                                    width=\"100%\">\n" +
                "                                    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"icons_block block-1\"\n" +
                "                                           role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\"\n" +
                "                                           width=\"100%\">\n" +
                "                                        <tr>\n" +
                "                                            <td class=\"pad\"\n" +
                "                                                style=\"vertical-align: middle; color: #9d9d9d; font-family: inherit; font-size: 15px; padding-bottom: 5px; padding-top: 5px; text-align: center;\">\n" +
                "                                                <table cellpadding=\"0\" cellspacing=\"0\" role=\"presentatio\"\n" +
                "                                                       style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\"\n" +
                "                                                       width=\"100%\">\n" +
                "                                                    <tr>\n" +
                "                                                        <td class=\"alignment\"\n" +
                "                                                            style=\"vertical-align: middle; text-align: center;\">\n" +
                "                                                            <!--[if vml]>\n" +
                "                                                            <table align=\"left\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                                                                   role=\"presentation\"\n" +
                "                                                                   style=\"display:inline-block;padding-left:0px;padding-right:0px;mso-table-lspace: 0pt;mso-table-rspace: 0pt;\">\n" +
                "                                                            <![endif]-->\n" +
                "                                                            <!--[if !vml]><!-->\n" +
                "                                                            <table cellpadding=\"0\" cellspacing=\"0\" class=\"icons-inner\"\n" +
                "                                                                   role=\"presentation\"\n" +
                "                                                                   style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; display: inline-block; margin-right: -4px; padding-left: 0px; padding-right: 0px;\">\n" +
                "                                                                <!--<![endif]-->\n" +
                "                                                            </table>\n" +
                "                                                        </td>\n" +
                "                                                    </tr>\n" +
                "                                                </table>\n" +
                "                                            </td>\n" +
                "                                        </tr>\n" +
                "                                    </table>\n" +
                "                                </td>\n" +
                "                            </tr>\n" +
                "                            </tbody>\n" +
                "                        </table>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "                </tbody>\n" +
                "            </table>\n" +
                "        </td>\n" +
                "    </tr>\n" +
                "    </tbody>\n" +
                "</table><!-- End -->\n" +
                "</body>\n" +
                "</html>";
    }

}
