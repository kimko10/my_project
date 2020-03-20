<!--결과페이지-->
<!--메뉴얼 '승인페이지 작성' 승인응답 파라미터 포함.-->

<!DOCTYPE html>
<html style="height: 100%;">
<head>
<meta name="robots" content="noindex, nofollow">
<meta http-equiv="content-type" content="text/html; charset=euc-kr">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, target-densitydpi=medium-dpi" />
<title>EasyPay 8.0 webpay mobile</title>
<link rel="stylesheet" type="text/css" href="./css/easypay.css" />
<link rel="stylesheet" type="text/css" href="./css/board.css" />
</head>
<script type="text/javascript">
<%!
    /*
     * 파라미터 체크 메소드
     */
    public String getNullToSpace(String param)
    {
        return (param == null) ? "" : param.trim();
    }
%>
<%
    request.setCharacterEncoding("euc-kr");

    String res_cd           = getNullToSpace(request.getParameter("res_cd"));              //결과코드             
    String res_msg          = getNullToSpace(request.getParameter("res_msg"));             //결과메시지           
    String cno              = getNullToSpace(request.getParameter("cno"));                 //PG거래번호           
    String amount           = getNullToSpace(request.getParameter("amount"));              //총 결제금액          
    String order_no         = getNullToSpace(request.getParameter("order_no"));            //주문번호             
    String auth_no          = getNullToSpace(request.getParameter("auth_no"));             //승인번호             
    String tran_date        = getNullToSpace(request.getParameter("tran_date"));           //승인일시             
    String escrow_yn        = getNullToSpace(request.getParameter("escrow_yn"));           //에스크로 사용유무    
    String complex_yn       = getNullToSpace(request.getParameter("complex_yn"));          //복합결제 유무        
    String stat_cd          = getNullToSpace(request.getParameter("stat_cd"));             //상태코드             
    String stat_msg         = getNullToSpace(request.getParameter("stat_msg"));            //상태메시지           
    String pay_type         = getNullToSpace(request.getParameter("pay_type"));            //결제수단           
    String card_no          = getNullToSpace(request.getParameter("card_no"));             //카드번호             
    String issuer_cd        = getNullToSpace(request.getParameter("issuer_cd"));           //발급사코드           
    String issuer_nm        = getNullToSpace(request.getParameter("issuer_nm"));           //발급사명             
    String acquirer_cd      = getNullToSpace(request.getParameter("acquirer_cd"));         //매입사코드           
    String acquirer_nm      = getNullToSpace(request.getParameter("acquirer_nm"));         //매입사명             
    String install_period   = getNullToSpace(request.getParameter("install_period"));      //할부개월             
    String noint            = getNullToSpace(request.getParameter("noint"));               //무이자여부                     
    String part_cancel_yn   = getNullToSpace(request.getParameter("part_cancel_yn"));      //부분취소 가능여부    
    String card_gubun       = getNullToSpace(request.getParameter("card_gubun"));          //신용카드 종류        
    String card_biz_gubun   = getNullToSpace(request.getParameter("card_biz_gubun"));      //신용카드 구분  
    String cpon_flag        = getNullToSpace(request.getParameter("cpon_flag"));           //쿠폰 사용유무        
    String bank_cd          = getNullToSpace(request.getParameter("bank_cd"));             //은행코드             
    String bank_nm          = getNullToSpace(request.getParameter("bank_nm"));             //은행명               
    String account_no       = getNullToSpace(request.getParameter("account_no"));          //계좌번호             
    String deposit_nm       = getNullToSpace(request.getParameter("deposit_nm"));          //입금자명             
    String expire_date      = getNullToSpace(request.getParameter("expire_date"));         //계좌사용만료일       
    String cash_res_cd      = getNullToSpace(request.getParameter("cash_res_cd"));         //현금영수증 결과코드  
    String cash_res_msg     = getNullToSpace(request.getParameter("cash_res_msg"));        //현금영수증 결과메세지
    String cash_auth_no     = getNullToSpace(request.getParameter("cash_auth_no"));        //현금영수증 승인번호  
    String cash_tran_date   = getNullToSpace(request.getParameter("cash_tran_date"));      //현금영수증 승인일시  
    String cash_issue_type  = getNullToSpace(request.getParameter("cash_issue_type"));     //현금영수증 발행용도   
    String cash_auth_type   = getNullToSpace(request.getParameter("cash_auth_type"));      //인증구분             
    String cash_auth_value  = getNullToSpace(request.getParameter("cash_auth_value"));     //현금영수증 인증번호
    String auth_id          = getNullToSpace(request.getParameter("auth_id"));             //PhoneID              
    String billid           = getNullToSpace(request.getParameter("billid"));              //인증번호             
    String mobile_no        = getNullToSpace(request.getParameter("mobile_no"));           //휴대폰번호           
    String mob_ansim_yn     = getNullToSpace(request.getParameter("mob_ansim_yn"));        //안심결제 사용유무             
    String cp_cd            = getNullToSpace(request.getParameter("cp_cd"));               //포인트사/쿠폰사 
    String rem_amt          = getNullToSpace(request.getParameter("rem_amt"));             //잔액     
    String bk_pay_yn        = getNullToSpace(request.getParameter("bk_pay_yn"));           //장바구니 결제여부     
    String canc_acq_date    = getNullToSpace(request.getParameter("canc_acq_date"));       //매입취소일시        
    String canc_date        = getNullToSpace(request.getParameter("canc_date"));           //취소일시         
    String refund_date      = getNullToSpace(request.getParameter("refund_date"));         //환불예정일시    

%>
</script>
</head>
<body id="container_skyblue">
<form name="frm_pay" method="post">  
<div id="div_mall">
   <div class="contents1">
            <div class="con1">
                <p>
                    <img src='./img/common/logo.png' height="19" alt="Easypay">
                </p>
            </div>
            <div class="con1t1">
                <p>EP8.0 Webpay Mobile<br>결과 페이지</p>
            </div>
    </div>
    <div class="contents">
        <section class="section00 bg_skyblue">
            <fieldset>
                <legend>주문</legend>
                <br>
                <div class="roundTable">
                    <table width="100%" class="table_roundList" cellpadding="5">          
                        <!-- ##########  인증요청 파라미터 ########## -->   
                        <tbody>
                            <tr>
                                <td colspan="2">결과코드</td>
                                <td class="r">[<%=res_cd %>]</td>
                            </tr>
                            <tr>
                                <td colspan="2">결과메세지</td>
                                <td class="r"><%=res_msg %></td>
                            </tr>
                            <tr>
                                <td colspan="2">PG거래번호</td>
                                <td class="r"><%=cno %></td>
                            </tr>
                            <tr>
                                <td colspan="2">총 결제금액</td>
                                <td class="r"><%=amount %></td>
                            </tr>
                            <tr>
                                <td colspan="2">주문번호</td>
                                <td class="r"><%=order_no %></td>
                            </tr>
                            <tr>
                                <td colspan="2">승인번호</td>
                                <td class="r"><%=auth_no %></td>
                            </tr>
                            <tr>
                                <td colspan="2">승인일시</td>
                                <td class="r"><%=tran_date %></td>
                            </tr>
                            <tr>
                                <td colspan="2">에스크로여부</td>
                                <td class="r"><%=escrow_yn %></td>
                            </tr>
                            <tr>
                                <td colspan="2">복합결제여부</td>
                                <td class="r"><%=complex_yn %></td>
                            </tr>
                            <tr>
                                <td colspan="2">상태코드</td>
                                <td class="r"><%=stat_cd %></td>
                            </tr>
                            <tr>
                                <td colspan="2">상태메시지</td>
                                <td class="r"><%=stat_msg %></td>
                            </tr>
                            <tr>
                                <td colspan="2">결제수단</td>
                                <td class="r"><%=pay_type %></td>
                            </tr>
                            <tr>
                                <td colspan="2">카드번호</td>
                                <td class="r"><%=card_no %></td>
                            </tr>
                            <tr>
                                <td colspan="2">발급사</td>
                                <td class="r">[<%=issuer_cd %>] <%=issuer_nm %></td>
                            </tr>
                            <tr>
                                <td colspan="2">매입사</td>
                                <td class="r">[<%=acquirer_cd %>] <%=acquirer_nm %></td>
                            </tr>
                            <tr>
                                <td colspan="2">할부개월</td>
                                <td class="r"><%=install_period %></td>
                            </tr>
                            <tr>
                                <td colspan="2">무이자여부</td>
                                <td class="r"><%=noint %></td>
                            </tr>
                            <tr>
                                <td colspan="2">부분취소 가능여부</td>
                                <td class="r"><%=part_cancel_yn %></td>
                            </tr>
                            <tr>
                                <td colspan="2">신용카드종류</td>
                                <td class="r"><%=card_gubun %></td>
                            </tr>
                            <tr>
                                <td colspan="2">신용카드구분</td>
                                <td class="r"><%=card_biz_gubun %></td>
                            </tr>
                            <tr>
                                <td colspan="2">쿠폰 사용유무</td>
                                <td class="r"><%=cpon_flag %></td>
                            </tr>  
                             <tr>
                                <td colspan="2">은행코드</td>
                                <td class="r"><%=bank_cd %></td>
                            </tr>  
                             <tr>
                                <td colspan="2">은행명</td>
                                <td class="r"><%=bank_nm %></td>
                            </tr>  
                             <tr>
                                <td colspan="2">계좌번호</td>
                                <td class="r"><%=account_no %></td>
                            </tr>  
                             <tr>
                                <td colspan="2">입금자명</td>
                                <td class="r"><%=deposit_nm %></td>
                            </tr>  
                             <tr>
                                <td colspan="2">계좌사용만료일</td>
                                <td class="r"><%=expire_date %></td>
                            </tr>  
                             <tr>
                                <td colspan="2">현금영수증 결과코드</td>
                                <td class="r"><%=cash_res_cd %></td>
                            </tr>  
                             <tr>
                                <td colspan="2">현금영수증 결과메세지</td>
                                <td class="r"><%=cash_res_msg %></td>
                            </tr>      
                             <tr>
                                <td colspan="2">현금영수증 승인번호</td>
                                <td class="r"><%=cash_auth_no %></td>
                            </tr> 
                             <tr>
                                <td colspan="2">현금영수증 승인일시</td>
                                <td class="r"><%=cash_tran_date %></td>
                            </tr> 
                             <tr>
                                <td colspan="2">현금영수증 발행용도</td>
                                <td class="r"><%=cash_issue_type %></td>
                            </tr> 
                             <tr>
                                <td colspan="2">현금영수증 인증구분</td>
                                <td class="r"><%=cash_auth_type %></td>
                            </tr> 
                             <tr>
                                <td colspan="2">현금영수증 인증번호</td>
                                <td class="r"><%=cash_auth_value %></td>
                            </tr> 
                            <tr>
                                <td colspan="2">휴대폰 PhoneID</td>
                                <td class="r"><%=auth_id %></td>
                            </tr>
                            <tr>
                                <td colspan="2">휴대폰 인증번호</td>
                                <td class="r"><%=billid %></td>
                            </tr>
                            <tr>
                                <td colspan="2">휴대폰번호</td>
                                <td class="r"><%=mobile_no %></td>
                            </tr>
                            <tr>
                                <td colspan="2">안심결제 사용유무</td>
                                <td class="r"><%=mob_ansim_yn %></td>
                            </tr>
                            <tr>
                                <td colspan="2">포인트사/쿠폰사</td>
                                <td class="r"><%=cp_cd %></td>
                            </tr>       
                            <tr>
                                <td colspan="2">잔액</td>
                                <td class="r"><%=rem_amt %></td>
                            </tr>  
                            <tr>
                                <td colspan="2">장바구니 결제여부</td>
                                <td class="r"><%=bk_pay_yn %></td>
                            </tr>                          
                            <tr>
                                <td colspan="2">매입취소일시</td>
                                <td class="r"><%=canc_acq_date %></td>
                            </tr>                 
                            <tr>
                                <td colspan="2">취소일시</td>
                                <td class="r"><%=canc_date %></td>
                            </tr>    
                            <tr>
                                <td colspan="2">환불예정일시</td>
                                <td class="r"><%=refund_date %></td>
                            </tr>                              
                        </tbody>
                    </table>
                </div>
            </fieldset>
        </section>
    </div>
</div>
</body>
</html>