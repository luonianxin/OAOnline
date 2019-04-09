package com.lnx.oa.service;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.jbpm.api.ProcessDefinition;

public interface IProcessDefinitionService {

	public List<ProcessDefinition> findLastList();

	public void deploy(File resource);

	public void deleteBykey(String key);

	public InputStream getImageInputStream(String processDefinitonId);

}
