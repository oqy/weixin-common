package com.minyisoft.webapp.weixin.common.model.dto.receive.messagenode;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class ScanCodeConverter implements Converter {

	@SuppressWarnings("rawtypes")
	@Override
	public boolean canConvert(Class type) {
		return ScanCodeInfo.class == type;
	}

	@Override
	public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
		ScanCodeInfo scanCode = new ScanCodeInfo();
		while (reader.hasMoreChildren()) {
			reader.moveDown();
			if (reader.getNodeName().equalsIgnoreCase("ScanType")) {
				scanCode.setScanType(reader.getValue());
			} else if (reader.getNodeName().equalsIgnoreCase("ScanResult")) {
				scanCode.setScanResult(reader.getValue());
			}
			reader.moveUp();
		}
		return scanCode;
	}

}
