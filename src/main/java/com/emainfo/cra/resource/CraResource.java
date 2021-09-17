package com.emainfo.cra.resource;

import com.emainfo.cra.dto.CraDto;
import com.emainfo.cra.dto.WorkDayDto;
import com.emainfo.cra.model.Cra;
import com.emainfo.cra.model.MediaTypeUtils;
import com.emainfo.cra.service.CraService;
import com.querydsl.core.types.Predicate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.util.*;

import java.util.List;

@RestController
@Slf4j
@Validated
@RequiredArgsConstructor
@RequestMapping("/cras")
@CrossOrigin()
public class CraResource {
    private final CraService craService;
    private static final String DIRECTORY = ".";
    private static final String DEFAULT_FILE_NAME = "craRepport.pdf";

    @Autowired
    private ServletContext servletContext;

    // http://localhost:8080/download1?fileName=abc.zip
    // Using ResponseEntity<InputStreamResource>
    @GetMapping("/download")
    public void downloadFile3(HttpServletResponse resonse,
                              @RequestParam(defaultValue = DEFAULT_FILE_NAME) String fileName) throws IOException {

        MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, fileName);
        System.out.println("fileName: " + fileName);
        System.out.println("mediaType: " + mediaType);

        File file = new File(DIRECTORY + "/" + fileName);

        // Content-Type
        // application/pdf
        resonse.setContentType(mediaType.getType());

        // Content-Disposition
        resonse.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName());

        // Content-Length
        resonse.setContentLength((int) file.length());

        BufferedInputStream inStream = new BufferedInputStream(new FileInputStream(file));
        BufferedOutputStream outStream = new BufferedOutputStream(resonse.getOutputStream());

        byte[] buffer = new byte[1024];
        int bytesRead = 0;
        while ((bytesRead = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
        outStream.flush();
        inStream.close();
    }


    @GetMapping("/dynamic")
    public Flux<CraDto> dynamicFindAll(final HttpServletRequest req,@QuerydslPredicate(root = Cra.class) final Predicate predicate) {
        log.info("Dynamically find all cra ");

        return craService.retrieveAll(predicate);
    }

    @GetMapping
    public Flux<CraDto> getListCraByUsername(final HttpServletRequest req) {
    	final String username= req.getHeader("username");
    	log.info("find all cra by user name {}", username);
    	//if(Objects.nonNull(username) && !username.isEmpty()) {
    		return craService.getListCraByUserName(username);
    	//}


    }

    @GetMapping("/{id}")
    public Mono<CraDto> retrieveById(
            @Schema(implementation = String.class) @PathVariable(value = "id") final Long id) {
        log.info("find cra for id {}", id);

        return craService.retrieveById(id);
    }
    @GetMapping("/{year}/{month}")
    public Flux<CraDto> retreiveByDate(Integer year,Integer month){
        return craService.getCraByDate(year,month);
    }

    @PostMapping("/pdf")
    public void generatePdf(@RequestBody Map<String, Object> craMap) throws JRException, FileNotFoundException {
        Map params = new HashMap();
        List<WorkDayDto> listWorkDay = new ArrayList<WorkDayDto>();
        List<Map<String,Object>> workDays = (List<Map<String,Object>>)craMap.get("workDay");
        List<String> daysNames = (List<String>)craMap.get("daysNames");
        for(int i = 0 ; i<workDays.size();i++){
            int dayOfMonth = (Integer) workDays.get(i).get("dayOfMonth");
            String workedTime = (String) workDays.get(i).get("workedTime");
            String dayName = daysNames.get(i);
            WorkDayDto workDay = new WorkDayDto(dayOfMonth,dayName,workedTime,0);
            listWorkDay.add(workDay);

        }
        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource (listWorkDay);
        JRBeanCollectionDataSource craDetails = new JRBeanCollectionDataSource (Collections.singleton(craMap));
        params.put("datasource",ds);
        log.info("{}",craMap.get("signature"));
        JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/pdf/craRepport.jrxml"));

        JasperPrint report = JasperFillManager.fillReport(compileReport,params,craDetails);
        JasperExportManager.exportReportToPdfFile(report,"craRepport.pdf");

    }


    @PostMapping
    public Mono<CraDto> createNew(
            @RequestBody @Valid final CraDto craDto) {
        log.info("Receiving {} Data", craDto);
        return craService.createCraByUserName(craDto);
    }

    @PutMapping("/{id}")
    public Mono<CraDto> update(
            @Schema(implementation = String.class) @PathVariable(value = "id") final Long id,
            @RequestBody @Valid final CraDto craDto) {
        log.info("Updating cra  {} Data", id);
        return craService.update(id, craDto);
    }

    @DeleteMapping("/{id}")
    public Mono<CraDto> delete(
            @Schema(implementation = String.class) @PathVariable(value = "id") final Long id) {
        log.info("Updating cra  {} Data", id);
        return craService.delete(id);
    }
}