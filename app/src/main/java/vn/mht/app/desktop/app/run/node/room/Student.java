/*
        Licensed to the Apache Software Foundation (ASF) under one
        or more contributor license agreements.  See the NOTICE file
        distributed with this work for additional information
        regarding copyright ownership.  The ASF licenses this file
        to you under the Apache License, Version 2.0 (the
        "License"); you may not use this file except in compliance
        with the License.  You may obtain a copy of the License at

          http://www.apache.org/licenses/LICENSE-2.0

        Unless required by applicable law or agreed to in writing,
        software distributed under the License is distributed on an
        "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
        KIND, either express or implied.  See the License for the
        specific language governing permissions and limitations
        under the License.
 */

package vn.mht.app.desktop.app.run.node.room;

import javafx.beans.property.SimpleBooleanProperty;

public class Student {

    private static int studentIdAct = 0;
    private int studentId;
    private String name;
    private GENDER gender;

    private final SimpleBooleanProperty selected;

    enum GENDER {
        MALE,
        FEMALE
    }

    public Student(String name, GENDER gender, boolean selected) {
        this.selected = new SimpleBooleanProperty(false);
        studentId = studentIdAct++;
        this.name = name;
        this.gender = gender;
        this.selected.set(selected);
    }

    public Student(String name, GENDER gender) {
        this.selected = new SimpleBooleanProperty(false);
        studentId = studentIdAct++;
        this.name = name;
        this.gender = gender;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GENDER getGender() {
        return gender;
    }

    public void setGender(GENDER gender) {
        this.gender = gender;
    }

    public boolean getSelected() {
        return selected.get();
    }

    public void setSelected(boolean selected) {
        this.selected.set(selected);
    }

    public SimpleBooleanProperty selectedProperty() {
        return selected;
    }
}
