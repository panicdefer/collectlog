/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.flume.sink.elasticsearch.serializer;


public enum SerializerType {

	STRING(org.apache.flume.sink.elasticsearch.serializer.StringSerializer.class),
	INTEGER(org.apache.flume.sink.elasticsearch.serializer.IntegerSerializer.class),
	INT(org.apache.flume.sink.elasticsearch.serializer.IntegerSerializer.class),
	DOUBLE(org.apache.flume.sink.elasticsearch.serializer.DoubleSerializer.class),
	DATE(org.apache.flume.sink.elasticsearch.serializer.DateSerializer.class);

	private final Class<? extends Serializer> builderClass;

	private SerializerType(Class<? extends Serializer> builderClass) {
		this.builderClass = builderClass;
	}

	public Class<? extends Serializer> getBuilderClass() {
		return builderClass;
	}
}
