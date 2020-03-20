<%@ page contentType="text/html; charset=euc-kr" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<title>KICC EASYPAY8.0 SAMPLE</title>
<meta name="robots" content="noindex, nofollow">
<meta http-equiv="content-type" content="text/html; charset=euc-kr">
<link href="/css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/js/default.js"></script>
<%!
    /**
     * �Ķ���� üũ �޼ҵ�
     */
    public String getNullToSpace(String param)
    {
        return (param == null) ? "" : param.trim();
    }
%>
<%
    request.setCharacterEncoding("euc-kr");

    // CA : ī�����, CAO : ī����οɼ� 
    // CC : ī��������, CCO : ī�������ҿɼ�, CPC : ī�������� 

    String res_cd               = getNullToSpace(request.getParameter("res_cd"));           // �����ڵ�          (CA, CAO, CC, CCO, CPC)
    String res_msg              = getNullToSpace(request.getParameter("res_msg"));          // ����޽���        (CA, CAO, CC, CCO, CPC)
    String cno                  = getNullToSpace(request.getParameter("cno"));              // PG�ŷ���ȣ        (CA, CAO, CC, CCO, CPC)
    String amount               = getNullToSpace(request.getParameter("amount"));           // �� �����ݾ�       (CA,                  )
    String order_no             = getNullToSpace(request.getParameter("order_no"));         // �ֹ���ȣ          (CA,                  )
    String auth_no              = getNullToSpace(request.getParameter("auth_no"));          // ���ι�ȣ          (CA,                  )
    String tran_date            = getNullToSpace(request.getParameter("tran_date"));        // �����Ͻ�          (CA,      CC,      CPC)
    String escrow_yn            = getNullToSpace(request.getParameter("escrow_yn"));        // ����ũ�� ������� (CA,                  )
    String complex_yn           = getNullToSpace(request.getParameter("complex_yn"));       // ���հ��� ����     (CA,                  )
    String stat_cd              = getNullToSpace(request.getParameter("stat_cd"));          // �����ڵ�          (CA,      CC,      CPC)
    String stat_msg             = getNullToSpace(request.getParameter("stat_msg"));         // ���¸޽���        (CA,      CC,      CPC)
    String pay_type             = getNullToSpace(request.getParameter("pay_type"));         // ��������          (CA,                  )
    String mall_id              = getNullToSpace(request.getParameter("mall_id"));          // ������ Mall ID    (CA                   )
    String card_no              = getNullToSpace(request.getParameter("card_no"));          // ī���ȣ          (CA,          CCO     )
    String issuer_cd            = getNullToSpace(request.getParameter("issuer_cd"));        // �߱޻��ڵ�        (CA,          CCO     )
    String issuer_nm            = getNullToSpace(request.getParameter("issuer_nm"));        // �߱޻��          (CA,          CCO     )
    String acquirer_cd          = getNullToSpace(request.getParameter("acquirer_cd"));      // ���Ի��ڵ�        (CA,          CCO     )
    String acquirer_nm          = getNullToSpace(request.getParameter("acquirer_nm"));      // ���Ի��          (CA,          CCO     )
    String install_period       = getNullToSpace(request.getParameter("install_period"));   // �Һΰ���          (CA,          CCO     )
    String noint                = getNullToSpace(request.getParameter("noint"));            // �����ڿ���        (CA                   )
    String part_cancel_yn       = getNullToSpace(request.getParameter("part_cancel_yn"));   // �κ���� ���ɿ��� (CA                   )
    String card_gubun           = getNullToSpace(request.getParameter("card_gubun"));       // �ſ�ī�� ����     (CA                   )
    String card_biz_gubun       = getNullToSpace(request.getParameter("card_biz_gubun"));   // �ſ�ī�� ����     (CA                   )
    String cpon_flag            = getNullToSpace(request.getParameter("cpon_flag"));        // ���� �������     (    CAO,     CCO     )
    String used_cpon            = getNullToSpace(request.getParameter("used_cpon"));        // ���� ���ݾ�     (    CAO              )
    String canc_acq_date        = getNullToSpace(request.getParameter("canc_acq_date"));    // ��������Ͻ�      (                  CPC)
    String canc_date            = getNullToSpace(request.getParameter("canc_date"));        // ����Ͻ�          (CC,               CPC)
    String account_no           = getNullToSpace(request.getParameter("account_no"));       // ���¹�ȣ          (CC,                  )
%>

<body>
<table border="0" width="910" cellpadding="10" cellspacing="0">
    <tr>
        <td>
            <table border="0" width="900" cellpadding="0" cellspacing="0">
                <tr>
                    <td height="30" bgcolor="#FFFFFF" align="left">&nbsp;<img src="/img/arow3.gif" border="0" align="absmiddle">&nbsp;<b>���</b></td>
                </tr>
                <tr>
                    <td height="2" bgcolor="#2D4677"></td>
                </tr>
            </table>
            <table border="0" width="900" cellpadding="0" cellspacing="0">
                <tr>
                    <td height="5"></td>
                </tr>
            </table>
            <table border="0" width="1000" cellpadding="0" cellspacing="1" bgcolor="#DCDCDC">
                <tr>
                    <td height="25" bgcolor="#EDEDED" width="150">&nbsp;�����ڵ�</td>
                    <td bgcolor="#FFFFFF" width="180">&nbsp;<%=res_cd%></td>
                    <td bgcolor="#EDEDED" width="150">&nbsp;����޽���</td>
                    <td bgcolor="#FFFFFF" width="180">&nbsp;<%=res_msg%></td>
                    <td height="25" bgcolor="#EDEDED" width="150">&nbsp;PG�ŷ���ȣ</td>
                    <td bgcolor="#FFFFFF" width="180">&nbsp;<%=cno%></td>
                </tr>
                <tr>
                    <td height="25" bgcolor="#EDEDED">&nbsp;�� �����ݾ�</td>
                    <td bgcolor="#FFFFFF">&nbsp;<%=amount%></td>
                    <td height="25" bgcolor="#EDEDED">&nbsp;�ֹ���ȣ</td>
                    <td bgcolor="#FFFFFF">&nbsp;<%=order_no%></td>
                    <td height="25" bgcolor="#EDEDED">&nbsp;���ι�ȣ</td>
                    <td bgcolor="#FFFFFF">&nbsp;<%=auth_no%></td>
                </tr>
                <tr>
                    <td height="25" bgcolor="#EDEDED">&nbsp;�����Ͻ�</td>
                    <td bgcolor="#FFFFFF">&nbsp;<%=tran_date%></td>
                    <td height="25" bgcolor="#EDEDED">&nbsp;����ũ�� �������</td>
                    <td bgcolor="#FFFFFF">&nbsp;<%=escrow_yn%></td>
                    <td height="25" bgcolor="#EDEDED">&nbsp;���հ��� ����</td>
                    <td bgcolor="#FFFFFF">&nbsp;<%=complex_yn%></td>
                </tr>
                <tr>
                    <td height="25" bgcolor="#EDEDED">&nbsp;�����ڵ�</td>
                    <td bgcolor="#FFFFFF">&nbsp;<%=stat_cd%></td>
                    <td height="25" bgcolor="#EDEDED">&nbsp;���¹�ȣ</td>
                    <td bgcolor="#FFFFFF">&nbsp;<%=account_no%></td>
                    <td height="25" bgcolor="#EDEDED">&nbsp;��������</td>
                    <td bgcolor="#FFFFFF">&nbsp;<%=pay_type%></td>
                </tr>
                <tr>
                    <td height="25" bgcolor="#EDEDED">&nbsp;������ Mall ID</td>
                    <td bgcolor="#FFFFFF">&nbsp;<%=mall_id%></td>
                    <td height="25" bgcolor="#EDEDED">&nbsp;ī���ȣ</td>
                    <td bgcolor="#FFFFFF">&nbsp;<%=card_no%></td>
                    <td height="25" bgcolor="#EDEDED">&nbsp;�߱޻��ڵ�</td>
                    <td bgcolor="#FFFFFF">&nbsp;<%=issuer_cd%></td>
                </tr>
                <tr>
                    <td height="25" bgcolor="#EDEDED">&nbsp;�߱޻��</td>
                    <td bgcolor="#FFFFFF">&nbsp;<%=issuer_nm%></td>
                    <td height="25" bgcolor="#EDEDED">&nbsp;���Ի��ڵ�</td>
                    <td bgcolor="#FFFFFF">&nbsp;<%=acquirer_cd%></td>
                    <td height="25" bgcolor="#EDEDED">&nbsp;���Ի��</td>
                    <td bgcolor="#FFFFFF">&nbsp;<%=acquirer_nm%></td>
                </tr>
                <tr>
                    <td height="25" bgcolor="#EDEDED">&nbsp;�Һΰ���</td>
                    <td bgcolor="#FFFFFF">&nbsp;<%=install_period%></td>
                    <td bgcolor="#EDEDED">&nbsp;�����ڿ���</td>
                    <td bgcolor="#FFFFFF">&nbsp;<%=noint%></td>
                    <td bgcolor="#EDEDED">&nbsp;</td>
                    <td bgcolor="#FFFFFF">&nbsp;</td>
                </tr>
                <tr>
                    <td height="25" bgcolor="#EDEDED">&nbsp;�κ���� ���ɿ���</td>
                    <td bgcolor="#FFFFFF">&nbsp;<%=part_cancel_yn%></td>
                    <td bgcolor="#EDEDED">&nbsp;�ſ�ī�� ����</td>
                    <td bgcolor="#FFFFFF">&nbsp;<%=card_gubun%></td>
                    <td height="25" bgcolor="#EDEDED">&nbsp;�ſ�ī�� ����</td>
                    <td bgcolor="#FFFFFF">&nbsp;<%=card_biz_gubun%></td>
                </tr>
                <tr>
                    <td height="25" bgcolor="#EDEDED">&nbsp;���� �������</td>
                    <td bgcolor="#FFFFFF">&nbsp;<%=cpon_flag%></td>
                    <td bgcolor="#EDEDED">&nbsp;���� ���ݾ�</td>
                    <td bgcolor="#FFFFFF">&nbsp;<%=used_cpon%></td>
                    <td bgcolor="#EDEDED">&nbsp;��������Ͻ�</td>
                    <td bgcolor="#FFFFFF">&nbsp;<%=canc_acq_date%></td>
                </tr>
                <tr>
                    <td height="25" bgcolor="#EDEDED">&nbsp;����Ͻ�</td>
                    <td bgcolor="#FFFFFF">&nbsp;<%=canc_date%></td>
                    <td bgcolor="#EDEDED">&nbsp;</td>
                    <td bgcolor="#FFFFFF">&nbsp;</td>
                    <td bgcolor="#EDEDED">&nbsp;</td>
                    <td bgcolor="#FFFFFF">&nbsp;</td>
                </tr>
            </table>
        </td>
    </tr>
</table>
</form>
</body>
</html>