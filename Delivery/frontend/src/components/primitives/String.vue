<template>
    <div>
        <div v-if="editMode" style="margin-top:-20px;">
            <v-text-field 
                    :label="label" 
                    v-model="value"
                    @change="change"
            />
        </div>
        <div v-else>
             :  
        </div>
    </div>
</template>

<script>  
    export default {
        name: 'String',
        components:{
        },
        props: {
            value:{
                type: String,
                default: ''
            },
            editMode: Boolean,
            label: String
        },
        methods:{
            change(){
                this.$emit("input", this.value);
            }
        }
    }
</script>
