<%@ page contentType="text/html; charset=euc-kr" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>KICC EASYPAY 8.0 SAMPLE</title>
<meta name="robots" content="noindex, nofollow">
<meta http-equiv="content-type" content="text/html; charset=euc-kr">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>
<meta http-equiv="Pragma" content="no-cache"/>
<link href="/css/style.css" rel="stylesheet" type="text/css">
<script language="javascript" src="/js/default.js" type="text/javascript"></script>
<!-- Test -->
<script type="text/javascript" src="http://testpg.easypay.co.kr/webpay/EasypayCard_Web.js"></script>
<!-- Real -->
<!-- script type="text/javascript" src="https://pg.easypay.co.kr/webpay/EasypayCard_Web.js"></script-->

<script type="text/javascript">

    /* �Է� �ڵ� Setting */
    function f_init()
    {
        var frm_pay = document.frm_pay;

        var today = new Date();
        var year  = today.getFullYear();
        var month = today.getMonth() + 1;
        var date  = today.getDate();
        var time  = today.getTime();

        if(parseInt(month) < 10)
        {
            month = "0" + month;
        }

        if(parseInt(date) < 10)
        {
            date = "0" + date;
        }


        /*--����--*/        
        frm_pay.EP_mall_id.value        = "T5102001";                              //������ ID
        frm_pay.EP_mall_nm.value        = "��������8.0 �����";                    //��������
        frm_pay.EP_order_no.value       = "ORDER_" + year + month + date + time;   //������ �ֹ���ȣ    
                                                                                   //��������(select)
        frm_pay.EP_currency.value       = "00";                                    //��ȭ�ڵ� : 00-��
        frm_pay.EP_product_nm.value     = "�׽�Ʈ��ǰ";                            //��ǰ��
        frm_pay.EP_product_amt.value    = "100";                                 //��ǰ�ݾ�
                                                                                   //������ return_url(������ Ÿ�� ���� ��, �б�)
        frm_pay.EP_lang_flag.value      = "KOR"                                    //���: KOR / ENG
        frm_pay.EP_charset.value        = "EUC-KR"                                 //������ Charset: EUC-KR(default) / UTF-8
        frm_pay.EP_user_id.value        = "psj1988";                               //������ �� ID
        frm_pay.EP_memb_user_no.value   = "15123485756";                           //������ �� �Ϸù�ȣ
        frm_pay.EP_user_nm.value        = "ȫ�浿";                                //������ ����
        frm_pay.EP_user_mail.value      = "kildong@kicc.co.kr";                    //������ �� �̸���
        frm_pay.EP_user_phone1.value    = "0221471111";                            //������ �� ��ȣ1
        frm_pay.EP_user_phone2.value    = "01012345679";                           //������ �� ��ȣ2
        frm_pay.EP_user_addr.value      = "����� ��õ�� ���굿";                  //������ �� �ּ�
        frm_pay.EP_product_type.value   = "0";                                     //��ǰ�������� : 0-�ǹ�, 1-����
        frm_pay.EP_product_expr.value   = "20201231";                              //���񽺱Ⱓ : YYYYMMDD
//        frm_pay.EP_return_url.value     = "http://������URL/web/normal/order_res.jsp";      // Return ���� URL (HTTP���� �Է�)
        frm_pay.EP_return_url.value     = "http://192.168.100.78:8080/ezpay/pc/normal/order_res";      // Return ���� URL (HTTP���� �Է�)

                                        
        /*--�ſ�ī��--*/                    
        frm_pay.EP_usedcard_code.value  = "";                                      //��밡���� ī�� LIST
        frm_pay.EP_quota.value          = "";                                      //�Һΰ���
 
                                                                                   //������ ����(Y/N) (select)   
        frm_pay.EP_noinst_term.value    = "029-02:03";                             //�����ڱⰣ
                                                                                   //ī�������Ʈ ��뿩��(select) 
        frm_pay.EP_point_card.value     = "029-40";                                //����Ʈī�� LIST
                                                                                   //�����ڵ�(select)
                                                                                   //���� ��ī�� ���(select)                                                                                  
                                                                                                                   
        /*--�������--*/                        
        frm_pay.EP_vacct_bank.value     = "";                                      //������� ��밡���� ���� LIST 
        frm_pay.EP_vacct_end_date.value = "20231231";                              //�Ա� ���� ��¥
        frm_pay.EP_vacct_end_time.value = "153025";                                //�Ա� ���� �ð�
        
        

    }

    /* ����â ȣ��, ���� ��û */
    function f_cert()
    {

        var frm_pay = document.frm_pay;
        
        /*  �ֹ����� Ȯ�� */
        if( !frm_pay.EP_order_no.value ) 
        {
            alert("�������ֹ���ȣ�� �Է��ϼ���!!");
            frm_pay.EP_order_no.focus();
            return;
        }

        if( !frm_pay.EP_product_amt.value ) 
        {
            alert("��ǰ�ݾ��� �Է��ϼ���!!");
            frm_pay.EP_product_amt.focus();
            return;
        } 

        /* UTF-8 ��밡������ ��� EP_charset �� ���� �ʼ� */
        if( frm_pay.EP_charset.value == "UTF-8" )
        {
            // �ѱ��� ���� ���� ��� encoding �ʼ�.
            frm_pay.EP_mall_nm.value        = encodeURIComponent( frm_pay.EP_mall_nm.value );
            frm_pay.EP_product_nm.value     = encodeURIComponent( frm_pay.EP_product_nm.value );
            frm_pay.EP_user_nm.value        = encodeURIComponent( frm_pay.EP_user_nm.value );
            frm_pay.EP_user_addr.value      = encodeURIComponent( frm_pay.EP_user_addr.value );
        }


        /* ���������� ���ϴ� ����â ȣ�� ����� ���� */

        if( frm_pay.EP_window_type.value == "iframe" )
        {
            easypay_webpay(frm_pay,"/ezpay/pc/normal/iframe_req","hiddenifr","0","0","iframe",30);
            if( frm_pay.EP_charset.value == "UTF-8" )
            {
                // encoding �� ���� ��� decoding �ʼ�.
                frm_pay.EP_mall_nm.value        = decodeURIComponent( frm_pay.EP_mall_nm.value );
                frm_pay.EP_product_nm.value     = decodeURIComponent( frm_pay.EP_product_nm.value );
                frm_pay.EP_user_nm.value        = decodeURIComponent( frm_pay.EP_user_nm.value );
                frm_pay.EP_user_addr.value      = decodeURIComponent( frm_pay.EP_user_addr.value );
            }
        }
        else if( frm_pay.EP_window_type.value == "popup" )
        {
            easypay_webpay(frm_pay,"/ezpay/pc/normal/popup_req","hiddenifr","","","popup",30);

            if( frm_pay.EP_charset.value == "UTF-8" )
            {
                // encoding �� ���� ��� decoding �ʼ�.
                frm_pay.EP_mall_nm.value        = decodeURIComponent( frm_pay.EP_mall_nm.value );
                frm_pay.EP_product_nm.value     = decodeURIComponent( frm_pay.EP_product_nm.value );
                frm_pay.EP_user_nm.value        = decodeURIComponent( frm_pay.EP_user_nm.value );
                frm_pay.EP_user_addr.value      = decodeURIComponent( frm_pay.EP_user_addr.value );
            }
        }
    }

    function f_submit()
    {
        var frm_pay = document.frm_pay;

        frm_pay.target = "_self";
        frm_pay.action = "/ezpay/pc/easypay_request";
        frm_pay.submit();
    }

</script>
</head>
<body onload="f_init();">
<form name="frm_pay" method="post" action="">

<!--------------------------->
<!-- ::: ���� ���� ��û �� -->
<!--------------------------->

<input type="hidden" id="EP_mall_nm"        name="EP_mall_nm"           value="">         <!-- ��������-->
<input type="hidden" id="EP_currency"       name="EP_currency"          value="00">       <!-- ��ȭ�ڵ� // 00 : ��ȭ-->
<input type="hidden" id="EP_return_url"     name="EP_return_url"        value="">         <!-- ������ CALLBACK URL // -->
<input type="hidden" id="EP_ci_url"         name="EP_ci_url"            value="">         <!-- CI LOGO URL // -->
<input type="hidden" id="EP_lang_flag"      name="EP_lang_flag"         value="">         <!-- ��� // -->
<input type="hidden" id="EP_charset"        name="EP_charset"           value="EUC-KR">   <!-- ������ CharSet // EUC-KR,UTF-8 ���� �빮�� �̿�-->
<input type="hidden" id="EP_user_id"        name="EP_user_id"           value="">         <!-- ������ ��ID // -->
<input type="hidden" id="EP_memb_user_no"   name="EP_memb_user_no"      value="">         <!-- ������ ���Ϸù�ȣ // -->
<input type="hidden" id="EP_user_nm"        name="EP_user_nm"           value="">         <!-- ������ ���� // -->
<input type="hidden" id="EP_user_mail"      name="EP_user_mail"         value="">         <!-- ������ �� E-mail // -->
<input type="hidden" id="EP_user_phone1"    name="EP_user_phone1"       value="">         <!-- ������ �� ����ó1 // -->
<input type="hidden" id="EP_user_phone2"    name="EP_user_phone2"       value="">         <!-- ������ �� ����ó2 // -->
<input type="hidden" id="EP_user_addr"      name="EP_user_addr"         value="">         <!-- ������ �� �ּ� // -->
<input type="hidden" id="EP_user_define1"   name="EP_user_define1"      value="">         <!-- ������ �ʵ�1 // -->
<input type="hidden" id="EP_user_define2"   name="EP_user_define2"      value="">         <!-- ������ �ʵ�2 // -->
<input type="hidden" id="EP_user_define3"   name="EP_user_define3"      value="">         <!-- ������ �ʵ�3 // -->
<input type="hidden" id="EP_user_define4"   name="EP_user_define4"      value="">         <!-- ������ �ʵ�4 // -->
<input type="hidden" id="EP_user_define5"   name="EP_user_define5"      value="">         <!-- ������ �ʵ�5 // -->
<input type="hidden" id="EP_user_define6"   name="EP_user_define6"      value="">         <!-- ������ �ʵ�6 // -->
<input type="hidden" id="EP_product_type"   name="EP_product_type"      value="">         <!-- ��ǰ�������� // -->
<input type="hidden" id="EP_product_expr"   name="EP_product_expr"      value="">         <!-- ���� �Ⱓ // (YYYYMMDD) -->
<input type="hidden" id="EP_disp_cash_yn"   name="EP_disp_cash_yn"      value="">         <!-- ���ݿ����� ȭ��ǥ�ÿ��� //��ǥ�� : "N", �׿�: DB��ȸ -->


<!--------------------------->
<!-- ::: ī�� ���� ��û �� -->
<!--------------------------->

<input type="hidden" id="EP_usedcard_code"      name="EP_usedcard_code"     value="">      <!-- ��밡���� ī�� LIST // FORMAT->ī���ڵ�:ī���ڵ�: ... :ī���ڵ� EXAMPLE->029:027:031 // �� : DB��ȸ-->
<input type="hidden" id="EP_quota"              name="EP_quota"             value="">      <!-- �Һΰ��� (ī���ڵ�-�Һΰ���) -->
<input type="hidden" id="EP_os_cert_flag"       name="EP_os_cert_flag"      value="2">     <!-- �ؿܾȽ�Ŭ�� ��뿩��(����Ұ�) // -->
<input type="hidden" id="EP_noinst_flag"        name="EP_noinst_flag"       value="">      <!-- ������ ���� (Y/N) // -->
<input type="hidden" id="EP_noinst_term"        name="EP_noinst_term"       value="">      <!-- ������ �Ⱓ (ī���ڵ�-�����Һΰ���) // -->
<input type="hidden" id="EP_set_point_card_yn"  name="EP_set_point_card_yn" value="">      <!-- ī�������Ʈ ��뿩�� (Y/N) // -->
<input type="hidden" id="EP_point_card"         name="EP_point_card"        value="">      <!-- ����Ʈī�� LIST  // -->
<input type="hidden" id="EP_join_cd"            name="EP_join_cd"           value="">      <!-- �����ڵ� // -->
<input type="hidden" id="EP_kmotion_useyn"      name="EP_kmotion_useyn"     value="Y">     <!-- ���ξ�ī�� ������� (Y/N)// -->

<!------------------------------->
<!-- ::: ������� ���� ��û �� -->
<!------------------------------->

<input type="hidden" id="EP_vacct_bank"         name="EP_vacct_bank"        value="">      <!-- ������� ��밡���� ���� LIST // -->
<input type="hidden" id="EP_vacct_end_date"     name="EP_vacct_end_date"    value="">      <!-- �Ա� ���� ��¥ // -->
<input type="hidden" id="EP_vacct_end_time"     name="EP_vacct_end_time"    value="">      <!-- �Ա� ���� �ð� // -->

<!------------------------------->
<!-- ::: ����ī�� ���� ��û �� -->
<!------------------------------->

<input type="hidden" id="EP_prepaid_cp"         name="EP_prepaid_cp"        value="">      <!-- ����ī�� CP // FORMAT->�ڵ�:�ڵ�: ... :�ڵ� EXAMPLE->CCB:ECB // �� : DB��ȸ-->

<!--------------------------------->
<!-- ::: ��������� ���� ��û �� -->
<!--------------------------------->

<input type="hidden" id="EP_res_cd"             name="EP_res_cd"            value="">      <!--  �����ڵ� // -->
<input type="hidden" id="EP_res_msg"            name="EP_res_msg"           value="">      <!--  ����޼��� // -->
<input type="hidden" id="EP_tr_cd"              name="EP_tr_cd"             value="">      <!--  ����â ��û���� // -->
<input type="hidden" id="EP_ret_pay_type"       name="EP_ret_pay_type"      value="">      <!--  �������� // -->
<input type="hidden" id="EP_ret_complex_yn"     name="EP_ret_complex_yn"    value="">      <!--  ���հ��� ���� (Y/N) // -->
<input type="hidden" id="EP_card_code"          name="EP_card_code"         value="">      <!--  ī���ڵ� (ISP:KVPī���ڵ� MPI:ī���ڵ�) // -->
<input type="hidden" id="EP_eci_code"           name="EP_eci_code"          value="">      <!--  MPI�� ��� ECI�ڵ� // -->
<input type="hidden" id="EP_card_req_type"      name="EP_card_req_type"     value="">      <!--  �ŷ����� // -->
<input type="hidden" id="EP_save_useyn"         name="EP_save_useyn"        value="">      <!--  ī��� ���̺� ���� (Y/N) // -->
<input type="hidden" id="EP_trace_no"           name="EP_trace_no"          value="">      <!--  ������ȣ // -->
<input type="hidden" id="EP_sessionkey"         name="EP_sessionkey"        value="">      <!--  ����Ű // -->
<input type="hidden" id="EP_encrypt_data"       name="EP_encrypt_data"      value="">      <!--  ��ȣȭ���� // -->
<input type="hidden" id="EP_spay_cp"            name="EP_spay_cp"           value="">      <!--  ������� CP �ڵ� // -->
<input type="hidden" id="EP_card_prefix"        name="EP_card_prefix"       value="">      <!--  �ſ�ī��prefix // -->
<input type="hidden" id="EP_card_no_7"          name="EP_card_no_7"         value="">      <!--  �ſ�ī���ȣ ��7�ڸ� // -->


<table border="0" width="910" cellpadding="10" cellspacing="0">
<tr>
    <td>
    <!-- title start -->
    <table border="0" width="900" cellpadding="0" cellspacing="0">
    <tr>
        <td height="30" bgcolor="#FFFFFF" align="left">&nbsp;<img src="/img/arow3.gif" border="0" align="absmiddle">&nbsp;�Ϲ� > <b>����</b></td>
    </tr>
    <tr>
        <td height="2" bgcolor="#2D4677"></td>
    </tr>
    </table>
    <!-- title end -->

    <!-- mallinfo start -->
    <table border="0" width="900" cellpadding="0" cellspacing="0">
    <tr>
        <td height="30" bgcolor="#FFFFFF">&nbsp;<img src="/img/arow2.gif" border="0" align="absmiddle">&nbsp;<b>����������</b>(*�ʼ�)</td>
    </tr>
    </table>

    <table border="0" width="900" cellpadding="0" cellspacing="1" bgcolor="#DCDCDC">
    <tr height="25">
        <td bgcolor="#EDEDED" width="150">&nbsp; *������ID</td>
        <td bgcolor="#FFFFFF" width="750" colspan="3">&nbsp;<input type="text" id="EP_mall_id" name="EP_mall_id" value="T5102001" size="50" maxlength="8" class="input_F"></td>
    </tr>
    </table>
    <!-- mallinfo end -->

    <!-- webpay start -->
    <table border="0" width="900" cellpadding="0" cellspacing="0">
    <tr>
        <td height="30" bgcolor="#FFFFFF">&nbsp;<img src="/img/arow2.gif" border="0" align="absmiddle">&nbsp;<b>���� ����</b>(*�ʼ�)</td>
    </tr>
    </table>

    <table border="0" width="900" cellpadding="0" cellspacing="1" bgcolor="#DCDCDC">
    <tr height="25">
        <td bgcolor="#EDEDED" width="150">&nbsp; *��������</td>
        <td bgcolor="#FFFFFF" width="300">&nbsp;
            <select id="EP_pay_type" name="EP_pay_type" class="input_F">
                <option value="11" selected>�ſ�ī��</option>
                <option value="21">������ü</option>
                <option value="22">�������Ա�</option>
                <option value="31">�޴���</option>
                <option value="50">���Ұ���</option>
                <option value="60">�������</option>
            </select>
        </td>
        <td bgcolor="#EDEDED" width="150">&nbsp; ������ Ÿ��</td>
        <td bgcolor="#FFFFFF" width="300">&nbsp;
            <select id="EP_window_type" name="EP_window_type" class="input_F">
                <option value="iframe" selected>iframe</option>
                <option value="popup" >popup</option>
            </select>
       </td>
    </tr>
    <tr height="25">
        <td bgcolor="#EDEDED" width="150">&nbsp; *����Ÿ��</td>
        <td bgcolor="#FFFFFF" width="750" colspan="3">&nbsp;
            <select id="EP_cert_type" name="EP_cert_type" class="input_F">
                <option value="" selected>�Ϲ�</option>
                <option value="21">����</option>
                <option value="22">������</option>
            </select>
        </td>	
    </tr>
    </table>
    <!-- webpay end -->

    <!-- order start -->
    <table border="0" width="900" cellpadding="0" cellspacing="0">
    <tr>
        <td height="30" bgcolor="#FFFFFF">&nbsp;<img src="/img/arow2.gif" border="0" align="absmiddle">&nbsp;<b>�ֹ� ����</b>(*�ʼ�)</td>
    </tr>
    </table>

    <table border="0" width="900" cellpadding="0" cellspacing="1" bgcolor="#DCDCDC">
    <tr height="25">
        <td bgcolor="#EDEDED" width="150">&nbsp; *�ֹ���ȣ</td>
        <td bgcolor="#FFFFFF" width="750" colspan="3">&nbsp;<input type="text" id="EP_order_no" name="EP_order_no" value="" size="50" class="input_F"></td>
    </tr>
    <tr height="25">
        <td bgcolor="#EDEDED" width="150">&nbsp; ��ǰ��</td>
        <td bgcolor="#FFFFFF" width="300">&nbsp;<input type="text" id="EP_product_nm" name="EP_product_nm" value="�׽�Ʈ��ǰ" size="50" class="input_A"></td>
        <td bgcolor="#EDEDED" width="150">&nbsp; ��ǰ�ݾ�</td>
        <td bgcolor="#FFFFFF" width="300">&nbsp;<input type="text" id="EP_product_amt" name="EP_product_amt" value="50000" size="50" class="input_A"></td>
    </tr>
    </table>
    <!-- order end -->

    <table border="0" width="900" cellpadding="0" cellspacing="0">
    <tr>
        <td height="30" align="center" bgcolor="#FFFFFF"><input type="button" value="�� ��" class="input_D" style="cursor:hand;" onclick="javascript:f_cert();"></td>
    </tr>
    </table>
    </td>
</tr>
</table>
</form>
</body>
</html>