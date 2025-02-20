# Cài đặt cho dự án

<h2>Tải phần mềm NVM để quản lí thư viện NodeJS</h2>
<a href="/downloader/nvm-setup.exe" target="_blank" title="nvminstall">Tải NVM</a>

<h6> - Tải xong NVM làm theo như để sử dụng phiên bản của phiên bản NodeJS của dự án </h6>

```bash
    nvm install v22.11.0
    nvm use v22.11.0
```

ra được kết quả như này thì đúng

```bash
    node -v
    v22.11.0
```

## <h6>Rồi xài cmd/terminal đi thẳng vào ~/FE rồi xài lệnh này</h6>

- cd vào ~/FE sử dụng `nvm install` để download thư viện của NodeJS
- cd vào ~/FE rồi chạy bám lệnh này `npm run dev` để chạy được FE

<h2>Download Java 21 </h2>
<a href="https://www.oracle.com/java/technologies/downloads/#jdk21-windows" target="_blank" title="openjdk21" >Tải Java 21 nên tải file .msi</a>

- Cài đặt Java thì vào "Edit the system environment variables" có hình như dưới

![](./asset/set-java-env.png)

- Rồi hiện bảng này rồi bấm "Environment Variables..."

![](./asset/env-variable.png)

- Rồi nó hiện ra cái hộp thoại tìm nhìn cái User variable for `User`

![](./asset/pop-up-env.png)

- Tìm đến Path rồi nhấn Edit hiện ra hộp thoại rồi Edit Environment Variables rồi nhấn New để tạo ra ô để nhập
  rồi tìm đến đường dẫn Java `C:\Program Files\Java\jdk-21\bins (mặc định khi cài đặt Java cứ bấm next)`

- Check Java trong cmd/terminal bằng lệnh `java --version`, kết quả là:

```bash
    java --version
    java 21.0.6 2025-01-21 LTS
    Java(TM) SE Runtime Environment (build 21.0.6+8-LTS-188)
    Java HotSpot(TM) 64-Bit Server VM (build 21.0.6+8-LTS-188, mixed mode, sharing)
```

<h2>Extensions để chạy được Java trong Visual Studio Code</h2>

- Tải cái trong hình
  ![](./asset/java-ext.png)
  ![](./asset/spring-tool-ext.png)

- Cài đặt Java trong file JSON Visual Studio Code `nhấp tổ hợp phím Ctrl + P hoặc nhấp phím F1`
- Rồi gõ user ra được cái này
  ![](./asset/JSON-vscode.png)

- Rồi thêm cái dưới này

```json
    "java.configuration.runtimes": [
        {
        "name": "JavaSE-21",
        "path": "C:\\Program Files\\Java\\jdk-21",
        "default": true
        }
    ],
    "files.encoding": "utf8",
    "java.project.encoding": "setDefault",
    "boot-java.common.properties-metadata": "",
    "workbench.editorAssociations": {
        "*.jar": "default"
    },
    "java.completion.favoriteStaticMembers": [
        "org.junit.Assert.*",
        "org.junit.Assume.*",
        "org.junit.jupiter.api.Assertions.*",
        "org.junit.jupiter.api.Assumptions.*",
        "org.junit.jupiter.api.DynamicContainer.*",
        "org.junit.jupiter.api.DynamicTest.*",
        "org.mockito.Mockito.*",
        "org.mockito.ArgumentMatchers.*",
        "org.mockito.Answers.*"
    ],
    "java.project.encoding": "setDefault",
```

<h6>
    - Xong rồi mình khởi động lại máy chạy chương trình Java chung với JavaScript, 
    vì máy của ta cần reset mới chạy được chương trình Java của Visual Studio Code
</h6>
