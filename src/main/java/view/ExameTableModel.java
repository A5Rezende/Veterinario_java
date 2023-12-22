package View;

import java.util.List;
import model.Consulta;
import model.ConsultaDAO;
import model.Exame;
import model.ExameDAO;

/**
 *
 * @author Antonio
 */
public class ExameTableModel extends GenericTableModel {

    public ExameTableModel(List vDados) {
        super(vDados, new String[]{"id", "consulta", "descricao", "resultado"});
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            default:
                return String.class;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Exame exame = (Exame) vDados.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return exame.getId();
            case 1:
                Consulta consulta = ConsultaDAO.getInstance().retrieveById(exame.getId_consulta());
                if (consulta != null) {
                    return consulta.getId();
                }
                return "";
            case 2:
                return exame.getDescricao();
            case 3:
                return exame.getResultado();
                
            default:
                return "";
        }

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Exame exame = (Exame) vDados.get(rowIndex);

        switch (columnIndex) {
            case 0:
                exame.setId((int) aValue);
                break;
            case 1:
                exame.setId_consulta((int) aValue);
                break;
            case 2:
                exame.setDescricao((String) aValue);
                break;
            case 3:
                exame.setResultado((String) aValue);
                break;
            default:
                break;
        }

        ExameDAO.getInstance().update(exame);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

}