package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.Form;
import com.revature.repo.FormRepo;

@Service
public class FormServiceImpl implements FormService {
	
	private FormRepo formRepo;
	
	@Autowired
	public void setFormRepo(FormRepo formRepo) {
		this.formRepo=formRepo;
	}

	@Override
	public List<Form> getAllForms() {
		return formRepo.findAll();
	}

	@Override
	public Form getFormById(long formId) {
		return formRepo.findById(formId).get();
	}

	@Override
	public void createForm(Form form) {
		formRepo.save(form);

	}

	@Override
	public void updateFrom(Form form) {
		formRepo.findById(form.getFormId()).ifPresent((existingForm) -> formRepo.save(form));

	}

	@Override
	public void deleteForm(Form form) {
		formRepo.delete(form);

	}

}
