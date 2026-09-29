# Run in VS Code

1. Open this folder itself: `HangmanSC_Final`.
2. Install Microsoft **Extension Pack for Java**.
3. Make sure JDK 17 or newer is selected.
4. Open `Part_UI/GUI/Main.java`.
5. Click **Run** above `main`, or use Run and Debug -> **Run Hangman**.

The project intentionally keeps the requested top-level folders (`Domain`, `Part_Data`, `Part_Service`, `Part_UI/GUI`). `.vscode/settings.json` tells the Java extension that the workspace root is the package source root.

If VS Code still shows old red errors: Ctrl+Shift+P -> `Java: Clean Java Language Server Workspace` -> Restart.
