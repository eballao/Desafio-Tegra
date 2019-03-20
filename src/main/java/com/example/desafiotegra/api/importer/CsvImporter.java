package com.example.desafiotegra.api.importer;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.desafiotegra.api.model.Voo;
import com.example.desafiotegra.api.model.VooImportacao;
import com.example.desafiotegra.api.repository.VooRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

@Component
public class CsvImporter implements InitializingBean {
	
	private static final String SAMPLE_CSV_FILE_PATH = "src/main/resources/uberair.csv";
	
	@Autowired
	private VooRepository vooRepository;
	
	public List<VooImportacao> populaListaVoos() {
		
		try (
				Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH), StandardCharsets.UTF_8);
		) {
			CsvToBean<VooImportacao> csvToBean = new CsvToBeanBuilder<VooImportacao>(reader)
					.withType(VooImportacao.class)
					.withIgnoreLeadingWhiteSpace(true)
					.build();
			List<VooImportacao> voos = csvToBean.parse();
			return voos;
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		persistirVoosImportados();
	}

	public void persistirVoosImportados() {
		List<VooImportacao> voosCSV = populaListaVoos();
		List<Voo> voosImportados = new ArrayList<>();
		for (VooImportacao voo : voosCSV) {
			Voo novoVoo = new Voo(voo.getVoo(), voo.getOrigem(), voo.getDestino(), voo.getDataCSV().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
					voo.getSaidaCSV(), voo.getChegadaCSV(), voo.getPreco(), "UberAir");
			voosImportados.add(novoVoo);
		}
		
		vooRepository.saveAll(voosImportados);
	}
		

}
