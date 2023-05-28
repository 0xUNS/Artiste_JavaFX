package ma.emsi.artistapplication.service;

import ma.emsi.artistapplication.entities.Artiste;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonService {
	private static final ArtisteService artisteService = new ArtisteService();
	public static void exporter(String path) {
		List<Artiste> artistes = artisteService.findAll();

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.findAndRegisterModules();
			objectMapper.writeValue(new File("/home/abc/" + path), artistes);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void importer(String path) {
		for(Artiste artiste: readArtisteDataFromJson(path))
			if (!artisteService.artisteInList(artiste.getNom(), artiste.getPrenom(), artiste.getPseudoNom()))
				artisteService.save(artiste);
	}

	private static List<Artiste> readArtisteDataFromJson(String path) {
		File file = new File("/home/abc/" + path);

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.findAndRegisterModules();
			TypeFactory typeFactory = objectMapper.getTypeFactory();
			CollectionType collectionType = typeFactory.constructCollectionType(ArrayList.class, Artiste.class);
			return objectMapper.readValue(file, collectionType);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
}
