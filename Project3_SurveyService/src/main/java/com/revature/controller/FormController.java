package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.ChartData;
import com.revature.model.FormResponse;
import com.revature.service.FormResponseService;

@RestController
@CrossOrigin
public class FormController {

	private FormResponseService formResponseService;

	@Autowired
	public void setFormResponseService(FormResponseService formResponseService) {
		this.formResponseService = formResponseService;
	}

	@GetMapping("/batch/list")
	public List<String> getBatchNames() {
		return formResponseService.getBatchNames();
	}

	@GetMapping("/batch/weeks")
	public List<String> getBatchWeeks() {
		return formResponseService.getBatchWeeks();
	}

	@GetMapping("/batch/{name}")
	public List<FormResponse> getBatchNames(@PathVariable(name = "name") String batchName) {
		return formResponseService.getBatchForms(batchName);
	}

	@GetMapping("/batch/{name}/{week}")
	public List<FormResponse> getBatchNames(@PathVariable(name = "name") String batchName,
			@PathVariable(name = "week") String batchWeek) {
		System.out.println(batchName);
		System.out.println(batchWeek);
		return formResponseService.getBatchByNameAndWeek(batchName, batchWeek);
	}

	@GetMapping("/batch/chartdatabatch/week/{week}")
	public List<ChartData> getChartDataByWeek(@PathVariable(name = "week") String batchWeek) {
		return formResponseService.getChartDataByWeek(batchWeek);
	}

	@GetMapping("/batch/chartdatabatch/name/{name}")
	public List<ChartData> getChartDataByBatchName(@PathVariable(name = "name") String batchName) {
		return formResponseService.getChartDataByBatch(batchName);
	}

	@GetMapping("/batch/chartdatabatch/all")
	public ChartData getAllChartData() {
		return formResponseService.getAllChartData();
	}

	@GetMapping("/batch/chartdatabatch/all/week/{week}")
	public ChartData getBatchesAverageByWeek(@PathVariable(name = "week") String week) {
		return formResponseService.getBatchesAverageByWeek(week);
	}

	@GetMapping("/batch/chartdatabatch/all/batch/{batch}")
	public ChartData getWeeksAverageByBatch(@PathVariable(name = "batch") String batch) {
		return formResponseService.getWeeksAverageByBatch(batch);
	}

	@GetMapping("/batch/chartdatabatch/{name}/{week}")
	public ChartData getChartDataByBatchNameAndWeek(@PathVariable(name = "name") String batchName,
			@PathVariable(name = "week") String batchweek) {
		return formResponseService.getChartDataByBatchAndWeek(batchName, batchweek);
	}

}
