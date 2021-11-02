List<Map<String, Object>> ParamList = new ArrayList<Map<String, Object>>();
List<JRDataSource> SourceList = new ArrayList<JRDataSource>();

Map<String, Object> params = new HashMap<String, Object>();
params.put("Page1_param1", "value1_1");
params.put("Page1_param2", "value1_2");
params.put("Page1_param3", "value1_3");
..
params.put("Page2_param1", "value2_1");
params.put("Page2_param2", "value2_2");
params.put("Page2_param3", "value2_3");
..
params.put("Page3_param1", "value3_1");
params.put("Page3_param2", "value3_2");
params.put("Page3_param3", "value3_3");
..
..
List listResult_1 = //select table sql for example
List listResult_2 = //select table sql for example
List listResult_3 = //select table sql for example

JRDataSource dataSource_1 = new ListOfArrayDataSource(
listResult_1, new String[] {"LastName", "FirstName", "address"});

JRDataSource dataSource_2 = new ListOfArrayDataSource(
listResult_2, new String[] {"LastName", "FirstName", "address"});

JRDataSource dataSource_3 = new ListOfArrayDataSource(
listResult_3, new String[] {"LastName", "FirstName", "address"});

// Yes i know ! :D , we put the same params list then as i said befor every sub report will take its own parameters values, so don't worry about this task ;)
ParamList.add(params);
ParamList.add(params);
ParamList.add(params);

SourceList.add(dataSource_1);
SourceList.add(dataSource_2);
SourceList.add(dataSource_3);

File reportFile = // the jrxml file template of the report

// We can use also a list of reportFile, so that every page uses his own template :D


CreateReport(jasperReport, ParamList, SourceList);

}

Public void CreateReport(File reportFile, List<Map<String, Object>> ParamList,  List<JRDataSource> SourceList){

    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportFile.getPath());
    Map<String, Object> parameters = paramList.get(0);
    JRDataSource datasource = datasourceList.get(0);
    jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, datasource);

    if(paramList.size()>1){
        for(int i=1; i < paramList.size(); i++)
        {
            JasperPrint jasperPrint_next = JasperFillManager.fillReport(jasperReport, paramList.get(i), datasourceList.get(i));
             List pages = jasperPrint_next.getPages();
             for (int j = 0; j < pages.size(); j++) {
               JRPrintPage object = (JRPrintPage) pages.get(j);
               jasperPrint.addPage(object);
             }
        }
    }

}
