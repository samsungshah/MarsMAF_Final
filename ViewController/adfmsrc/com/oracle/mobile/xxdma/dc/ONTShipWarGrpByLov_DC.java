package com.oracle.mobile.xxdma.dc;

import com.oracle.mobile.xxdma.th.TransactionHeaderLov;
import com.oracle.mobile.xxdma.th.WSPayload;

import java.util.ArrayList;
import java.util.List;

import javax.el.ValueExpression;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.api.GenericTypeBeanSerializationHelper;
import oracle.adfmf.framework.exception.AdfException;
import oracle.adfmf.framework.exception.AdfInvocationException;
import oracle.adfmf.util.GenericType;

public class ONTShipWarGrpByLov_DC {
    public ONTShipWarGrpByLov_DC() {
        super();
    }
        private static List s_orgList = new ArrayList();
        String ShipGrpByList[] = null;
        public void AddOU(String l) {
            s_orgList.add(l);
            System.out.println("String Added to List s_orgList" + l);
        }

        public String[] getShipGrpByList() {
            if(ShipGrpByList == null){    
                try {
                System.out.println("Calling ProcessOU");
                 AddOU("");   
                ProcessOU();
            } catch (AdfInvocationException ex) {

                AdfException e = new AdfException("Error Invoking getShipGrpByList", AdfException.WARNING);
                System.out.println("Error Invoking getShipGrpByList");

            }
            ShipGrpByList = (String[]) s_orgList.toArray(new String[s_orgList.size()]);
            System.out.println("getShipGrpByList complete");
            }
            
            System.out.println("Inside getShipGrpByList");
           
            return ShipGrpByList;
        }

        public void ProcessOU() throws AdfInvocationException {
            
            
            List pnames = new ArrayList();
            List pvals = new ArrayList();
            List ptypes = new ArrayList();
            GenericType resultGT = null;
            ValueExpression ve = null;
            TransactionHeaderLov payloadTH = new TransactionHeaderLov();
            WSPayload payload = new WSPayload();
        
            payloadTH.setCallingSystemName("MAF");
            payloadTH.setCallingInterfaceName("GL");
            payloadTH.setDebugFlag("Y");
            
       //     ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.UserId}", String.class);
       //     String userId = (String) ve.getValue(AdfmfJavaUtilities.getAdfELContext());
       //     payloadTH.setUserId(userId);
            
       //     ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.RespId}", String.class);
       //     String respId = (String) ve.getValue(AdfmfJavaUtilities.getAdfELContext());
       //     payloadTH.setRespId(respId);
            
       //     ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.ApplnId}", String.class);
       //     String applnId = (String) ve.getValue(AdfmfJavaUtilities.getAdfELContext());
       //     payloadTH.setRespAppId(applnId);
            payloadTH.setUserId("1261");
            payloadTH.setRespId("50677");
           payloadTH.setRespAppId("660");
            
            payload.setTransactionHeader(payloadTH);
            
            GenericType payloadGT =  GenericTypeBeanSerializationHelper.toGenericType("ONTShipWarGrpByLov_WL.Types.process.TransactionHeader", payloadTH);
            
            pnames.add("TransactionHeader");
            ptypes.add(GenericType.class);
            pvals.add(payloadGT);

            try {
                resultGT =
                    (GenericType) AdfmfJavaUtilities.invokeDataControlMethod("ONTShipWarGrpByLov_WL", null, "process", pnames,
                                                                             pvals, ptypes);
                resultGT = resultGT.getParent();
                Object provider = AdfmfJavaUtilities.getDataControlProvider("ONTShipWarGrpByLov_WL");
                GenericTypeBeanSerializationHelper.fromGenericType(provider, resultGT);
                
                for (int i = 0; i < resultGT.getAttributeCount(); i++) {
                    GenericType entityGenericType = (GenericType) resultGT.getAttribute(i);
                    
                    for(int j = 0; j< entityGenericType.getAttributeCount(); j++) {
                        GenericType entityGenericType1 = (GenericType) entityGenericType.getAttribute(j);
                         Object OuName =entityGenericType1.getAttribute(0);
                        AddOU(OuName.toString());
                    }

                     
                }

            } catch (AdfInvocationException ex) {

                AdfException e = new AdfException("Error Invoking ProcessOU", AdfException.WARNING);
                System.out.println("Error Invoking ProcessOU");
            }
        }
        }

        //        System.out.println("Start ProcessOU");
        //        pnames.add("CallingSystemName");
        //        ptypes.add(String.class);
        //        pvals.add("MAF");
        //        pnames.add("CallingInterfaceName");
        //        ptypes.add(String.class);
        //        pvals.add("PO");
        //        pnames.add("DebugFlag");
        //        ptypes.add(String.class);
        //        pvals.add("Y");
        //        pnames.add("userId");
        //        ptypes.add(String.class);
        //        pvals.add("1261");
        //        pnames.add("respId");
        //        ptypes.add(String.class);
        //        pvals.add("50637");
        //        pnames.add("applId");
        //        ptypes.add(String.class);
        //        pvals.add("101");

