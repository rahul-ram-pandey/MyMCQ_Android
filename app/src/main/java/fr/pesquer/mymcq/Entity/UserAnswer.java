package fr.pesquer.mymcq.Entity;

import io.realm.RealmObject;

public class UserAnswer extends RealmObject {

    public String answer;
    public int mcqId;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getMcqId() {
        return mcqId;
    }

    public void setMcqId(int mcqId) {
        this.mcqId = mcqId;
    }
}
