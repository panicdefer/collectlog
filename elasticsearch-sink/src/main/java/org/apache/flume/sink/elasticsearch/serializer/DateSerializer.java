/*
 * Copyright(c) 2015 gvtv.com.cn All rights reserved.
 * distributed with this file and available online at
 * http://www.gvtv.com.cn/
 */
package org.apache.flume.sink.elasticsearch.serializer;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.apache.flume.Context;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Throwables;

/**
 * @version 1.0
 * @author Hyku
 */
public class DateSerializer implements Serializer {
	Logger logger = LoggerFactory.getLogger(DateSerializer.class);
	private String FIELD;
	private DateFormat FORMAT;
	private DateTimeFormatter DATETIMEFORMAT=DateTimeFormatter.ISO_DATE_TIME;
	private Boolean isBusiness;

	@Override
	public void initialize(Context context, String field) {
		this.FIELD = field;
		configure(context);
	}

	@Override
	public void serializer(XContentBuilder builder, String data) throws IOException {
		if (isBusiness) {
			builder.field(FIELD, LocalDateTime.parse(data,DATETIMEFORMAT));
		}else {
			try {
				builder.field(FIELD, FORMAT.parse(data));
			} catch (ParseException e) {
				Throwables.propagate(e);
			}
		}
	}

	@Override
	public void configure(Context context) {
		// TODO Auto-generated method stub
		String format = context.getString("format");
		String locale = context.getString("locale");
		String business = context.getString("business");
		if (business == null) {
			isBusiness = false;
			if (format == null) {
				FORMAT = new SimpleDateFormat();
			} else if (locale == null) {
				FORMAT = new SimpleDateFormat(format);
			} else {
				FORMAT = new SimpleDateFormat(format, new Locale(locale));
			}
		}else {
			isBusiness = true;
		}
		
	}

}
