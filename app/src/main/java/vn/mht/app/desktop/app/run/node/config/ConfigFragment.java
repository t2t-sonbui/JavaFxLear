package vn.mht.app.desktop.app.run.node.config;

import javafx.beans.binding.Binding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import vn.mht.app.desktop.FragmentNode;
import vn.mht.app.desktop.FragmentScreen;

import java.io.IOException;

public class ConfigFragment extends FragmentNode<ConfigViewModel> implements FragmentScreen {
    private Button btnScanPort, btnSave;
    private ComboBox<String> cbbPortBase, cbbPortDevice;
    private ObservableList<String> serialPortList;
    private ComboBox<SerialBaud> cbbBaudBase, cbbBaudDevice;
    private ObservableList<SerialBaud> baudObservableList;
    private ComboBox<Parity> cbbParityBase, cbbParityDevice;
    private ObservableList<Parity> parityObservableList;
    private ComboBox<Handshake> cbbHandshakeBase, cbbHandshakeDevice;
    private ObservableList<Handshake> handshakeObservableList;

    private final ConfigViewModel viewModel;
    private final FXMLLoader loader;
    private Node parentNode;

    public ConfigFragment(ConfigViewModel viewModel, FXMLLoader fxmlLoader) {
        this.viewModel = viewModel;
        loader = fxmlLoader;
        initViewComponent();
    }

    public Node getParentNode() {
        return parentNode;
    }

    public void initViewComponent() {

        serialPortList = FXCollections.observableArrayList();

        baudObservableList = FXCollections.observableArrayList();
        //add some Students
        baudObservableList.addAll(
                SerialBaud.BAUD_4800,
                SerialBaud.BAUD_9600,
                SerialBaud.BAUD_115200
        );

        parityObservableList = FXCollections.observableArrayList();
        parityObservableList.addAll(
                Parity.NONE,
                Parity.EVEN,
                Parity.ODD,
                Parity.MARK
        );
        handshakeObservableList = FXCollections.observableArrayList();
        handshakeObservableList.addAll(
                Handshake.OFF,
                Handshake.RST_CTS,
                Handshake.XON_XOFF
        );

        try {
            parentNode = loader.load();//Parent root = loader.load();
            btnScanPort = (Button) loader.getNamespace().get("btnScanPort");
            btnSave = (Button) loader.getNamespace().get("btnSave");
            cbbPortBase = (ComboBox<String>) loader.getNamespace().get("cbbPortBase");
            cbbPortDevice = (ComboBox<String>) loader.getNamespace().get("cbbPortDevice");
            cbbBaudBase = (ComboBox<SerialBaud>) loader.getNamespace().get("cbbBaudBase");
            cbbBaudDevice = (ComboBox<SerialBaud>) loader.getNamespace().get("cbbBaudDevice");
            cbbParityBase = (ComboBox<Parity>) loader.getNamespace().get("cbbParityBase");
            cbbParityDevice = (ComboBox<Parity>) loader.getNamespace().get("cbbParityDevice");
            cbbHandshakeBase = (ComboBox<Handshake>) loader.getNamespace().get("cbbHandshakeBase");
            cbbHandshakeDevice = (ComboBox<Handshake>) loader.getNamespace().get("cbbHandshakeDevice");
            initStage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initStage() {

        Binding<ObservableList<String>> binding = viewModel.getSerialPortNameBinding();
        cbbPortBase.itemsProperty().bind(binding);
        cbbPortBase.itemsProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.size() > 0) {
                cbbPortBase.getSelectionModel().select(0);
            }
        });

        cbbPortDevice.itemsProperty().bind(binding);
        cbbPortDevice.itemsProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.size() > 1) {
                cbbPortDevice.getSelectionModel().select(1);
            }
        });


        btnScanPort.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent paramT) {
//                viewModel.getListSerials();
                viewModel.triggerListSerialsEvent();
            }
        });
        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent paramT) {

            }
        });

//        Observable<ActionEvent> bttnEvents =JavaFxObservable.eventsOf(btnScanPort, ActionEvent.ACTION);

//        viewModel.getListSerialPortUpdate(ports -> {
//            serialPortList.setAll(ports);
//            if (serialPortList.size() > 0) {
//                cbbPortBase.getSelectionModel().select(0);
//            }
//        });
//        cbbPortBase.setItems(serialPortList);

        cbbBaudBase.setItems(baudObservableList);
        cbbBaudBase.getSelectionModel().select(SerialBaud.BAUD_9600);

        cbbBaudDevice.setItems(baudObservableList);
        cbbBaudDevice.getSelectionModel().select(SerialBaud.BAUD_9600);

        cbbParityBase.setItems(parityObservableList);
        cbbParityBase.getSelectionModel().select(Parity.NONE);

        cbbParityDevice.setItems(parityObservableList);
        cbbParityDevice.getSelectionModel().select(Parity.NONE);

        cbbHandshakeBase.setItems(handshakeObservableList);
        cbbHandshakeBase.getSelectionModel().select(Handshake.OFF);

        cbbHandshakeDevice.setItems(handshakeObservableList);
        cbbHandshakeDevice.getSelectionModel().select(Handshake.OFF);
    }


    @Override
    public void terminate() {
        System.out.println("Fragment terminate()");
        viewModel.dispose();
    }
}

enum SerialBaud {
    BAUD_4800(4800) {
        public String toString() {
            return "4800";
        }
    },
    BAUD_9600(9600) {
        public String toString() {
            return "9600";
        }
    },
    BAUD_115200(115200) {
        public String toString() {
            return "115200";
        }
    };

    private final int baud;

    SerialBaud(int b) {
        this.baud = b;
    }

    public int getBaud() {
        return this.baud;
    }
}

enum DataSize {
    SIZE_8(8) {
        public String toString() {
            return "8";
        }
    },
    SIZE_7(7) {
        public String toString() {
            return "7";
        }
    };

    private final int size;

    DataSize(int b) {
        this.size = b;
    }

    public int getDataSize() {
        return this.size;
    }
}

enum Parity {
    NONE(0) {
        public String toString() {
            return "NONE";
        }
    },
    EVEN(1) {
        public String toString() {
            return "EVEN";
        }
    },
    ODD(2) {
        public String toString() {
            return "ODD";
        }
    },
    MARK(3) {
        public String toString() {
            return "MARK";
        }
    };

    private final int value;

    Parity(int b) {
        this.value = b;
    }

    public int getValue() {
        return this.value;
    }
}

enum Handshake {
    OFF(0) {
        public String toString() {
            return "OFF";
        }
    },
    RST_CTS(1) {
        public String toString() {
            return "RST/CTS";
        }
    },
    XON_XOFF(2) {
        public String toString() {
            return "XON/XOFF";
        }
    };

    private final int value;

    Handshake(int b) {
        this.value = b;
    }

    public int getValue() {
        return this.value;
    }
}


