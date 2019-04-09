package com.lnx.oa.service;

import java.io.InputStream;
import java.util.List;

import com.lnx.oa.domain.Template;

public interface ITemplateService {

	public List<Template> findAll();

	public void save(Template model);

	public void deleteById(Long id);

	public Template findById(Long id);

	public void update(Template template);

	public InputStream getStreamById(Long id);

}
