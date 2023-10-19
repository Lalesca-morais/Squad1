package org.example.exeception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FieldMessage {
    private String campo;
    private List<String> mensagens;
}