package com.example.lab3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.fragment.app.Fragment;
import java.io.FileOutputStream;

public class InputFragment extends Fragment {
    private Spinner spinner;
    private RadioGroup rg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_input, container, false);

        spinner = v.findViewById(R.id.spinnerPhoneType);
        rg = v.findViewById(R.id.radioGroupBrands);
        Button ok = v.findViewById(R.id.buttonOk);
        Button open = v.findViewById(R.id.buttonOpenHistory);

        String[] data = {"Смартфон", "Кнопковий телефон", "Планшет"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, data);
        spinner.setAdapter(adapter);

        ok.setOnClickListener(view -> {
            int selected = rg.getCheckedRadioButtonId();
            if (selected == -1) {
                Toast.makeText(getActivity(), "Оберіть бренд!", Toast.LENGTH_SHORT).show();
                return;
            }
            RadioButton rb = v.findViewById(selected);
            String result = spinner.getSelectedItem().toString() + " - " + rb.getText();

            ((MainActivity)getActivity()).passData(result);
            saveToFile(result);
        });

        open.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), HistoryActivity.class);
            startActivity(intent);
        });

        return v;
    }

    private void saveToFile(String text) {
        try (FileOutputStream fos = getActivity().openFileOutput("lab_storage.txt", Context.MODE_APPEND)) {
            fos.write((text + "\n").getBytes());
            Toast.makeText(getActivity(), "Успішно записано у файл!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Помилка запису", Toast.LENGTH_SHORT).show();
        }
    }

    public void reset() {
        spinner.setSelection(0);
        rg.clearCheck();
    }
}