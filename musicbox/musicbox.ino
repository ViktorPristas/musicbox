
int state[] = {0,0,0};
int prevState[] = {0,0,0};

void setup() {
  Serial.begin(9600);
}

void loop() {
  int sensorValue1 = digitalRead(2);
  int sensorValue2 = digitalRead(3);
  int sensorValue3 = digitalRead(4);
  state[0] = sensorValue1;
  state[1] = sensorValue2;
  state[2] = sensorValue3;
  for (int i = 0; i < 3; i++) {
    if (state[i] != prevState[i]) {
      printArray();
      delay(100);
      sensorValue1 = digitalRead(2);
      sensorValue2 = digitalRead(3);
      sensorValue3 = digitalRead(4);
      prevState[0] = sensorValue1;
      prevState[1] = sensorValue2;
      prevState[2] = sensorValue3; 
      return;
    }
  }

  if (sensorValue1 == 1 && sensorValue2 == 0 && sensorValue3 == 0) {
    tone(8, 294);
  } else if (sensorValue1 == 0 && sensorValue2 == 1 && sensorValue3 == 0) {
    tone(8, 330);
  } else if (sensorValue1 == 0 && sensorValue2 == 0 && sensorValue3 == 1) {
    tone(8, 370);
  } else if (sensorValue1 == 1 && sensorValue2 == 1 && sensorValue3 == 0) {
    tone(8, 392);
  } else if (sensorValue1 == 1 && sensorValue2 == 0 && sensorValue3 == 1) {
    tone(8, 440);
  } else if (sensorValue1 == 0 && sensorValue2 == 1 && sensorValue3 == 1) {
    tone(8, 494);
  } else if (sensorValue1 == 1 && sensorValue2 == 1 && sensorValue3 == 1) {
    tone(8, 523);
  } else {
    noTone(8);
  }
  for (int i = 0; i < 3; i++) {
    prevState[i] = state[i];
  }
}

void printArray() {
  Serial.print("[");
  Serial.print(state[0]);
  Serial.print(", ");
  Serial.print(state[1]);
  Serial.print(", ");
  Serial.print(state[2]);
  Serial.println("]");
}
