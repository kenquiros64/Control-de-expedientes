class Paciente < ActiveRecord::Base
    resourcify
    
    validates_format_of :cedula, :with => /([1-9])-([0-9]){4}-([0-9]){4}/, :on => [ :create, :update ],  :message => "Ingresar de manera correcta"
    validates_presence_of :cedula , :on => [ :create, :update ],:message => "no puede estar vacío"
    validates_presence_of :nombre ,:on => [ :create, :update ], :message => "no puede estar vacío"
    validates_presence_of :apellido1 , :on => [ :create, :update ],:message => "no puede estar vacío"
    validates_presence_of :apellido2 , :on => [ :create, :update ],:message => "no puede estar vacío"
    validates_presence_of :fechaNacimiento ,:on => [ :create, :update ], :message => "no puede estar vacío"
    validates_presence_of :sexo,:on => [ :create, :update ], :message => "no puede estar vacío"
    validates_presence_of :nacionalidad,:on => [ :create, :update ], :message => "no puede estar vacío"
    validates_uniqueness_of :cedula, :on => [ :create, :update ],:message => "ya fue ingresada"
    
    
    has_many :telefonos,  dependent: :destroy 
    has_many :citum, dependent: :destroy
    has_and_belongs_to_many :observacions, dependent: :destroy, :join_table => :observacions_pacientes
    has_and_belongs_to_many :enfermedads, dependent: :destroy, :join_table => :enfermedads_pacientes
    
    has_and_belongs_to_many :medicos, :join_table => :medicos_pacientes
    
    accepts_nested_attributes_for :observacions, reject_if: :all_blank, allow_destroy: true
    accepts_nested_attributes_for :enfermedads, reject_if: :all_blank, allow_destroy: true
    accepts_nested_attributes_for :telefonos, reject_if: :all_blank, allow_destroy: true, limit: 5

    has_attached_file :image, styles: { small: "64x64", med: "100x100", large: "200x200" },
                              default_url: 'icon-user-default.png' 
    validates_attachment :image, :content_type => { :content_type => ["image/jpg","image/png","image/jpeg"],
                                                    :message => ', Solo imágenes jpg, jpeg y png' }
    
    has_many :emails,  dependent: :destroy
    
    accepts_nested_attributes_for :emails, reject_if: :all_blank, allow_destroy: true, limit: 5

    def self.search(search)
      if search
        where('cedula LIKE ? OR concat_ws(' ',nombre,apellido1) LIKE ? OR concat_ws(' ',apellido1,apellido2) LIKE ?', "%#{search}%","%#{search}%","%#{search}%")
      else
      end
    end

end
