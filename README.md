# java-dynamic-jasper-report


### Sample code to export from table model
```java
File file=new File("D:\");
File f = new PrintTable().printTable(table.getModel(), "Test Report", pageFormat).exportToExcel(file);
```

### Sample code to export with subreport
```java
//  set up report option form subreport
ReportOption reportOption =new ReportOption("", "No", "Customer", "Amount", new ReportOption("Product List", "No", "Item", "Total", new ReportOption("Detail", "No", "Type", "Qty")));

//  print print or export report
File file=new File("D:\");
File f = new PrintTableDetails().printTable(table.getModel(), "Test Report", pageFormat, reportOption).exportToExcel(file); 
```
All this sample from test package. and for more custom check code in class "Template_Report_Master_Detail.java"

DynamicJasper : https://github.com/intive-FDV/DynamicJasper</br>
My youtube : https://www.youtube.com/c/HelloWorld-Raven/featured

![2023-01-31_224005](https://user-images.githubusercontent.com/58245926/215814397-34b0494e-b590-42ff-afa7-d31fc73b8c7a.png)
