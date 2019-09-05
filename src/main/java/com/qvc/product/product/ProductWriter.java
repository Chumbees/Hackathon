package com.qvc.product.product;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

public class ProductWriter implements ItemWriter<Product> {
	private final Logger logger = LoggerFactory.getLogger(ProductWriter.class);

	@Override
	public void write(List<? extends Product> products) throws Exception {

		File dump = new File("C:/product_loader.json");
		FileWriter fileWriter = new FileWriter(dump, true);

		if (products != null && !products.isEmpty()) {
			logger.info("Put chunk to grid, first product: {}", products.get(0).getProductNumber());
			try {
				fileWriter.write(""); // empties file before writing
				for (Product product : products) {
					fileWriter.append(product.toString());
				}

				fileWriter.close();

				//String command = "loader-batch.bat";
				//Process powerShellProcess = Runtime.getRuntime().exec(command);
				// command = "powershell.exe
				// $Invoke-RestMethod \"http://localhost:9200/product_search_server/product/_bulk?pretty\" -Method Post -ContentType 'application/x-ndjson' -InFile \"product_loader.json\"
				// ";

				//powerShellProcess = Runtime.getRuntime().exec(command);
				//powerShellProcess.getOutputStream().close();

			} catch (Exception e) {
				System.out.println(e);
			}

		} else {
			logger.info("Where's my chunk");
		}
	}
}