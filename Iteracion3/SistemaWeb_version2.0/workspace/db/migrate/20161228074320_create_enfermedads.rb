class CreateEnfermedads < ActiveRecord::Migration
  def change
    create_table :enfermedads do |t|
      t.string :codigo
      t.string :descripcion

      t.timestamps null: false
    end
  end
end
