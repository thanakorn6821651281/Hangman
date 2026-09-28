# HangmanSC - VS Code version

## สำคัญ
โปรเจกต์นี้ใช้ Java 8+ เพื่อไม่ให้ VS Code ขึ้นแดงจาก `record`, switch expression และ API ใหม่ของ Java

เปิดโฟลเดอร์ `HangmanSC_FINAL_JAVA8` โดยตรงใน VS Code

## Run เกม
เปิด `Part_UI/GUI/Main.java` แล้วกด Run หรือ F5 เลือก `Run Hangman`.

## Run Test
กด F5 แล้วเลือก `Run Tests` หรือเปิด `test/TestRunner.java` แล้ว Run.

## Flow
HOME -> START GAME -> LOGIN -> DIFFICULTY -> GAME -> WIN/LOSE

NEXT = คำใหม่ในระดับเดิมที่เลือก ไม่เปลี่ยนความยาก
REPLAY = คำใหม่ในระดับเดิม

## CSV
- data/words/easy.csv = 50 คำ
- data/words/medium.csv = 50 คำ
- data/words/hard.csv = 50 คำ
- data/players/players.csv = ชื่อและคะแนน
