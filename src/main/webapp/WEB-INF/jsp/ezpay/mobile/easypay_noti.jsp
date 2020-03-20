<%@ page language="java" contentType="text/html; charset=euc-kr" %>
<%!
    /**
     * �Ķ���� üũ �޼ҵ�
     */
    public String getNullToSpace(String param) {
        return (param == null) ? "" : param.trim();
    }
%>
<%
    final char DELI_US = 0x1f;
    final String RESULT_SUCCESS = "0000";
    final String RESULT_FAIL 	= "5001";

    /* -------------------------------------------------------------------------- */
    /* ::: ��Ƽ���� - �Ϲ�                                                        */
    /* -------------------------------------------------------------------------- */
    String result_msg = "";

    String r_res_cd         = getNullToSpace(request.getParameter( "res_cd"         ));  // �����ڵ�
    String r_res_msg        = getNullToSpace(request.getParameter( "res_msg"        ));  // ���� �޽���
    String r_cno            = getNullToSpace(request.getParameter( "cno"            ));  // PG�ŷ���ȣ
    String r_memb_id        = getNullToSpace(request.getParameter( "memb_id"        ));  // ������ ID
    String r_amount         = getNullToSpace(request.getParameter( "amount"         ));  // �� �����ݾ�
    String r_order_no       = getNullToSpace(request.getParameter( "order_no"       ));  // �ֹ���ȣ
    String r_noti_type      = getNullToSpace(request.getParameter( "noti_type"      ));  // ��Ƽ���� ����(20), �Ա�(30), ����ũ�� ����(40)
    String r_auth_no        = getNullToSpace(request.getParameter( "auth_no"        ));  // ���ι�ȣ
    String r_tran_date      = getNullToSpace(request.getParameter( "tran_date"      ));  // �����Ͻ�
    String r_card_no        = getNullToSpace(request.getParameter( "card_no"        ));  // ī���ȣ
    String r_issuer_cd      = getNullToSpace(request.getParameter( "issuer_cd"      ));  // �߱޻��ڵ�
    String r_issuer_nm      = getNullToSpace(request.getParameter( "issuer_nm"      ));  // �߱޻��
    String r_acquirer_cd    = getNullToSpace(request.getParameter( "acquirer_cd"    ));  // ���Ի��ڵ�
    String r_acquirer_nm    = getNullToSpace(request.getParameter( "acquirer_nm"    ));  // ���Ի��
    String r_install_period = getNullToSpace(request.getParameter( "install_period" ));  // �Һΰ���
    String r_noint          = getNullToSpace(request.getParameter( "noint"          ));  // �����ڿ���
    String r_bank_cd        = getNullToSpace(request.getParameter( "bank_cd"        ));  // �����ڵ�
    String r_bank_nm        = getNullToSpace(request.getParameter( "bank_nm"        ));  // �����
    String r_account_no     = getNullToSpace(request.getParameter( "account_no"     ));  // ���¹�ȣ
    String r_deposit_nm     = getNullToSpace(request.getParameter( "deposit_nm"     ));  // �Ա��ڸ�
    String r_expire_date    = getNullToSpace(request.getParameter( "expire_date"    ));  // ���»�븸����
    String r_cash_res_cd    = getNullToSpace(request.getParameter( "cash_res_cd"    ));  // ���ݿ����� ����ڵ�
    String r_cash_res_msg   = getNullToSpace(request.getParameter( "cash_res_msg"   ));  // ���ݿ����� ����޽���
    String r_cash_auth_no   = getNullToSpace(request.getParameter( "cash_auth_no"   ));  // ���ݿ����� ���ι�ȣ
    String r_cash_tran_date = getNullToSpace(request.getParameter( "cash_tran_date" ));  // ���ݿ����� �����Ͻ�
    String r_cp_cd          = getNullToSpace(request.getParameter( "cp_cd"          ));  // ����Ʈ��
    String r_used_pnt       = getNullToSpace(request.getParameter( "used_pnt"       ));  // �������Ʈ
    String r_remain_pnt     = getNullToSpace(request.getParameter( "remain_pnt"     ));  // �ܿ��ѵ�
    String r_pay_pnt        = getNullToSpace(request.getParameter( "pay_pnt"        ));  // ����/�߻�����Ʈ
    String r_accrue_pnt     = getNullToSpace(request.getParameter( "accrue_pnt"     ));  // ��������Ʈ
    String r_escrow_yn      = getNullToSpace(request.getParameter( "escrow_yn"      ));  // ����ũ�� �������
    String r_canc_date      = getNullToSpace(request.getParameter( "canc_date"      ));  // ����Ͻ�
    String r_canc_acq_date  = getNullToSpace(request.getParameter( "canc_acq_date"  ));  // ��������Ͻ�
    String r_refund_date    = getNullToSpace(request.getParameter( "refund_date"    ));  // ȯ�ҿ����Ͻ�
    String r_pay_type       = getNullToSpace(request.getParameter( "pay_type"       ));  // ��������
    String r_auth_cno       = getNullToSpace(request.getParameter( "auth_cno"       ));  // �����ŷ���ȣ
    String r_tlf_sno        = getNullToSpace(request.getParameter( "tlf_sno"        ));  // ä���ŷ���ȣ
    String r_account_type   = getNullToSpace(request.getParameter( "account_type"   ));  // ä������ Ÿ�� US AN 1 (V-�Ϲ���, F-������)

    /* -------------------------------------------------------------------------- */
    /* ::: ��Ƽ���� - ����ũ�� ���º���                                           */
    /* -------------------------------------------------------------------------- */
    String r_escrow_yn      = getNullToSpace(request.getParameter( "escrow_yn"      ));  // ����ũ������
    String r_stat_cd        = getNullToSpace(request.getParameter( "stat_cd "       ));  // ���濡��ũ�λ����ڵ�
    String r_stat_msg       = getNullToSpace(request.getParameter( "stat_msg"       ));  // ���濡��ũ�λ��¸޼���


    if ( r_res_cd.equals("0000") )
    {
	    /* ---------------------------------------------------------------------- */
	    /* ::: ������ DB ó��                                                     */
	    /* ---------------------------------------------------------------------- */
	    /* DBó�� ���� �� : res_cd=0000, ���� �� : res_cd=5001                    */
	    /* ---------------------------------------------------------------------- */
		if	// DBó�� ���� ��
		{
			result_msg = "res_cd=" + RESULT_SUCCESS + DELI_US + "res_msg=SUCCESS";
		}
		else // DBó�� ���� ��
		{
    		result_msg = "res_cd=" + RESULT_FAIL + DELI_US + "res_msg=FAIL";
		}
    }

    /* -------------------------------------------------------------------------- */
    /* ::: ��Ƽ ó����� ó��                                                     */
    /* -------------------------------------------------------------------------- */
    out.println( result_msg );

%>